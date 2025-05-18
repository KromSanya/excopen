package excopen.backend.controllers;

import excopen.backend.constants.Role;
import excopen.backend.dto.*;
import excopen.backend.entities.User;
import excopen.backend.iservices.IUserService;
import excopen.backend.mapper.UserMapper;
import excopen.backend.security.CurrentUser;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(IUserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/apply-guide")
    public ResponseEntity<String> applyForGuide(@CurrentUser User user,
                                                @RequestBody @Valid GuideRequestDto guideRequestDto) {
        userService.requestGuideRole(user.getId(), guideRequestDto);
        return ResponseEntity.ok("Код отправлен на номер " + guideRequestDto.getContacts().getPhone());
    }

    @PostMapping("/confirm-guide")
    public ResponseEntity<String> confirmGuide(@CurrentUser User user,
                                               @RequestBody ConfirmGuideDto dto) {
        boolean confirmed = userService.confirmGuideRole(user.getId(), dto.getPhoneNumber(), dto.getCode());
        return confirmed
                ? ResponseEntity.ok("Поздравляем, теперь вы гид!")
                : ResponseEntity.badRequest().body("Неверный код подтверждения");
    }


    @GetMapping("/test")
    public String testRoute() {
        return "Контроллер работает!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id,
                                                   @CurrentUser(required = false) User currentUser) {
        User targetUser = userService.getUserById(id);

        boolean isSelf = currentUser != null && currentUser.getId().equals(targetUser.getId());
        boolean isAdmin = currentUser != null && currentUser.getRole().equals(Role.ADMIN);
        boolean isGuide = targetUser.getRole().equals(Role.GUIDE);

        if (!isGuide && !isAdmin && !isSelf) {
            throw new AccessDeniedException("Нет прав на просмотр профиля этого пользователя.");
        }
        if(targetUser.getRole().equals(Role.GUIDE))
        {
            return ResponseEntity.ok(userMapper.toGuideResponseDTO(targetUser));
        }else
      //  if(targetUser.getRole().equals(Role.USER))
        {
            return ResponseEntity.ok(userMapper.toUserResponseDTO(targetUser));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(@CurrentUser User currentUser) {
            return ResponseEntity.ok(userMapper.toUserResponseDTO(currentUser));
    }

    @GetMapping("/test/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        // Создаем и заполняем основной DTO
        UserDTO dto = new UserDTO();
        dto.setId(123L);
        dto.setName("Тест");
        dto.setSurname("Пользователь");
        dto.setEmail("test@example.com");
        dto.setRole("GUIDE");
        dto.setAvatar("https://example.com/avatar.jpg");
        dto.setAvatarFile(null);
        dto.setTags(Arrays.asList("история", "искусство"));
        dto.setOrders(Collections.emptyList());
        dto.setTours(Collections.emptyList());
        dto.setRating(null);
        dto.setRatingCount(null);
        dto.setInfo("Тестовый аккаунт");

        // Создаем и заполняем контакты
        ContactDTO contacts = new ContactDTO();
        contacts.setPhone("+79991234567");
        contacts.setVk("@test_user");
        contacts.setTelegram("@test_tg");
        dto.setContacts(contacts);

        return ResponseEntity.ok(dto);
    }

    /// Только для разработки
    @GetMapping("/attributes")
    public Map<String, Object> userAttributes(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes();
    }

    @PutMapping(value = "/me", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserResponseDTO updateUser(
            @Valid @ModelAttribute UserUpdateDTO userUpdateDTO,
            @CurrentUser User user
    ) {
        userMapper.updateFromDTO(userUpdateDTO, user);
        User updatedUser = userService.updateUser(user, userUpdateDTO.getAvatarFile());
        return userMapper.toUserResponseDTO(updatedUser);
    }

    @PutMapping("/me/preferences-vector")
    public UserResponseDTO updatePreferencesVector(@RequestBody int[] preferencesVector,
                                                   @CurrentUser User user) {
        return userMapper.toUserResponseDTO(userService.updatePreferencesVector(user.getId(), preferencesVector));
    }

//    @DeleteMapping("/me")
//    public ResponseEntity<Void> deleteUser(@CurrentUser User user) {
//        userService.deleteUser(user.getId());
//        return ResponseEntity.noContent().build();
//    }



    @Data
    private static class UserDTO {
        private Long id;
        private String name;
        private String surname;
        private String email;
        private String role;
        private String avatar;
        private String avatarFile;
        private List<String> tags;
        private List<OrderDTO> orders;
        private List<TourDTO> tours;
        private Double rating;
        private Integer ratingCount;
        private ContactDTO contacts;
        private String info;

    }

    private static class OrderDTO {
    }

    private static class TourDTO {
    }




}
