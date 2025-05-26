package excopen.backend.servicesImpl;

import excopen.backend.constants.Role;
import excopen.backend.dto.GuideRequestDto;
import excopen.backend.entities.Review;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import excopen.backend.exceptions.NotFoundException;
import excopen.backend.iservices.IUserService;
import excopen.backend.repositories.ReviewRepository;
import excopen.backend.repositories.TourRepository;
import excopen.backend.repositories.UserRepository;
import excopen.backend.util.PhoneNumberValidator;
import excopen.backend.util.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends DefaultOAuth2UserService implements IUserService {


    private final UserRepository userRepository;
    private final VerificationService verificationService;
    private final PhoneNumberValidator phoneNumberValidator;
    private final ConcurrentMap<Long, GuideRequestDto> pendingGuideRequests = new ConcurrentHashMap<>();
    private final ReviewRepository reviewRepository;
    private final FileStorageService fileStorageService;
    private final TourRepository tourRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, VerificationService verificationService,
                           PhoneNumberValidator phoneNumberValidator, ReviewRepository reviewRepository, FileStorageService fileStorageService, TourRepository tourRepository) {
        this.userRepository = userRepository;
        this.verificationService = verificationService;
        this.phoneNumberValidator = phoneNumberValidator;
        this.reviewRepository = reviewRepository;
        this.fileStorageService = fileStorageService;
        this.tourRepository = tourRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public User getUserByGoogleId(String googleId) {
        return userRepository.findByGoogleId(googleId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(User user, MultipartFile avatarFile) {
        if (avatarFile != null && !avatarFile.isEmpty()) {
            String avatarPath = fileStorageService.storeUserAvatar(avatarFile);
            user.setAvatarUrl(avatarPath);
        }
        return userRepository.save(user);
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String googleId = (String) attributes.get("sub");

        User user;
        try {
            user = getUserByGoogleId(googleId);
        } catch (IllegalArgumentException e) {
            user = createUser(User.builder()
                    .googleId(googleId)
                    .name((String) attributes.get("given_name"))
                    .surname((String) attributes.get("family_name"))
                    .email((String) attributes.get("email"))
                    .avatarUrl((String) attributes.get("picture"))
                    .role(Role.USER)
                    .build());
        }

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );

        return new DefaultOAuth2User(authorities, attributes, "sub");
    }


    @Override
    public User updatePreferencesVector(Long userId, int[] preferencesVector) {
        User user = getUserById(userId);
        if (preferencesVector.length != user.getPreferencesVector().length) {
            throw new IllegalArgumentException("Vector length mismatch");
        }
        user.setPreferencesVector(preferencesVector);
        return userRepository.save(user);
    }


    @Override
    public int[] getUserPreferenceVector(Long userId) {
        return userRepository.findById(userId)
                .map(User::getPreferencesVector)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }


    @Transactional
    public void requestGuideRole(Long userId, GuideRequestDto guideRequestDto) {
        User user = getUserById(userId);
        if (user.getRole() == Role.GUIDE) {
            throw new IllegalArgumentException("Вы уже являетесь гидом");
        }

        if ((guideRequestDto.getContacts().getVk() == null || guideRequestDto.getContacts().getVk().isBlank()) &&
                (guideRequestDto.getContacts().getTelegram() == null || guideRequestDto.getContacts().getTelegram().isBlank())) {
            throw new IllegalArgumentException("Укажите хотя бы одну ссылку: VK или Telegram");
        }

        String normalizedPhone = phoneNumberValidator.normalizePhoneNumber(guideRequestDto.getContacts().getPhone());

        verificationService.sendVerificationCode(normalizedPhone);

        pendingGuideRequests.put(userId, guideRequestDto);
    }

    @Transactional
    public boolean confirmGuideRole(Long userId, String phoneNumber, String code) {
        String normalizedPhone = phoneNumberValidator.normalizePhoneNumber(phoneNumber);

        if (!verificationService.verifyCode(normalizedPhone, code)) {
            return false;
        }

        User user = getUserById(userId);

        GuideRequestDto guideRequestDto = pendingGuideRequests.remove(userId);
        if (guideRequestDto != null) {
            user.getContacts().setPhone(normalizedPhone);
            user.setDescription(guideRequestDto.getInfo());
            user.setCity(guideRequestDto.getCity());
        }

        user.setRole(Role.GUIDE);
        userRepository.save(user);

        return true;
    }

    public boolean isGuide(Long userId) {
        return getUserById(userId).getRole().equals(Role.GUIDE);
    }
  
    @Override
    public void updateGuideRating(Long userId) {
        Double avgRating = reviewRepository.calculateAverageRatingByCreatorId(userId);
        Integer reviewCount = reviewRepository.countReviewsByCreatorId(userId);

        User user = getUserById(userId);
        user.setGuideRating(avgRating != null ? avgRating : 0.0);
        user.setTotalReviews(reviewCount);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateGuideStats(Long guideId) {
        User guide = userRepository.findById(guideId)
                .orElseThrow(() -> new NotFoundException("Guide not found"));

        List<Tour> guideTours = tourRepository.findByCreator(guide);

        List<Double> validRatings = guideTours.stream()
                .map(Tour::getRating)
                .filter(Objects::nonNull)
                .toList();

        double averageRating = validRatings.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(Double.NaN);

        guide.setGuideRating(Double.isNaN(averageRating) ? null : Math.round(averageRating * 10) / 10.0);
        guide.setTotalReviews(guideTours.stream().mapToInt(Tour::getReviewCount).sum());

        userRepository.save(guide);
    }
}


