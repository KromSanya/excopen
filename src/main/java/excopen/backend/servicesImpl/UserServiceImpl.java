package excopen.backend.servicesImpl;

import excopen.backend.entities.User;
import excopen.backend.iservices.IUserService;
import excopen.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends DefaultOAuth2UserService implements IUserService {


    private final UserRepository userRepository;
    // private final UserFactory userFactory;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    @Override
    public User updateUser(Long userId, User user) {
        return userRepository.save(user);
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String googleId = (String) attributes.get("sub");
        try {
            getUserByGoogleId(googleId);
        } catch (IllegalArgumentException e) {
            createUser(User.builder()
                    .googleId(googleId)
                    .name((String) attributes.get("given_name"))
                    .surname((String) attributes.get("family_name"))
                    .email((String) attributes.get("email"))
                    .build());
        }
        return new DefaultOAuth2User(oAuth2User.getAuthorities(), attributes, "sub");
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


}


