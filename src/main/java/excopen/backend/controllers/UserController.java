package excopen.backend.controllers;

import excopen.backend.constants.Role;
import excopen.backend.dto.*;
import excopen.backend.dto.guidepromotion.*;
import excopen.backend.entities.User;
import excopen.backend.iservices.IUserService;
import excopen.backend.mapper.UserMapper;
import excopen.backend.security.CurrentUser;
import excopen.backend.util.VerificationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;
    private final VerificationService verificationService;

    @Autowired
    public UserController(IUserService userService, UserMapper userMapper, VerificationService verificationService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.verificationService = verificationService;
    }


    @PostMapping("/send-code")
    public ResponseEntity<?> sendVerificationCode(
            @CurrentUser User user,
            @RequestBody @Valid PhoneRequest request) {

        // Проверяем, не является ли уже пользователь гидом
        if (user.getRole() == Role.GUIDE) {
            return ResponseEntity.badRequest().body("Вы уже являетесь гидом");
        }

        try {
            verificationService.sendVerificationCode(user.getId(), request.getPhone());
            return ResponseEntity.ok("Код отправлен на номер " + request.getPhone());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(e.getMessage());
        }
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyForGuide(
            @CurrentUser User user,
            @RequestBody @Valid GuideApplicationRequest request) {
        try {
            userService.confirmGuideRole(
                    user.getId(),
                    request.getPhone(),
                    request.getCode(),
                    request.getInfo()
            );
            return ResponseEntity.ok("Поздравляем! Теперь вы гид");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


//    @PostMapping("/apply-guide")
//    public ResponseEntity<String> applyForGuide(@CurrentUser User user,
//                                                @RequestBody @Valid GuideRequestDto guideRequestDto) {
//        userService.requestGuideRole(user.getId(), guideRequestDto);
//        return ResponseEntity.ok("Код отправлен на номер " + guideRequestDto.getContacts().getPhone());
//    }
//
//    @PostMapping("/send-verification-code")
//    public ResponseEntity<String> sendVerificationCode(
//            @RequestBody @Valid PhoneVerificationRequest request) {
//
//        verificationService.sendVerificationCode(request.getPhoneNumber());
//        return ResponseEntity.ok("Код отправлен на номер " + request.getPhoneNumber());
//    }
//
//    @PostMapping("/verify-phone")
//    public ResponseEntity<String> verifyPhone(
//            @RequestBody @Valid PhoneVerificationDto dto) {
//
//        boolean verified = verificationService.verifyCode(
//                dto.getPhoneNumber(),
//                dto.getCode()
//        );
//
//        return verified
//                ? ResponseEntity.ok("Номер телефона подтвержден")
//                : ResponseEntity.badRequest().body("Неверный код подтверждения");
//    }


    @GetMapping("/test")
    public String testRoute() {
        return "Контроллер работает!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id,
                                          @CurrentUser(required = false) User currentUser,
                                          HttpServletRequest request) {
        User targetUser = userService.getUserById(id);

        boolean isSelf = currentUser != null && currentUser.getId().equals(targetUser.getId());
        boolean isAdmin = currentUser != null && currentUser.getRole().equals(Role.ADMIN);
        boolean isGuide = targetUser.getRole().equals(Role.GUIDE);

        if (!isGuide && !isAdmin && !isSelf) {
            throw new AccessDeniedException("Нет прав на просмотр профиля этого пользователя.");
        }
        if (targetUser.getRole().equals(Role.GUIDE)) {
            return ResponseEntity.ok(userMapper.toGuideResponseDTO(targetUser, request));
        } else
        //  if(targetUser.getRole().equals(Role.USER))
        {
            return ResponseEntity.ok(userMapper.toUserResponseDTO(targetUser, request));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(@CurrentUser User currentUser,
                                                          HttpServletRequest request) {
        return ResponseEntity.ok(userMapper.toUserResponseDTO(currentUser, request));
    }

    @PutMapping(value = "/me", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserResponseDTO updateUser(
            @Valid @RequestPart("me") UserUpdateDTO userUpdateDTO,
            @RequestPart(value = "avatarFile", required = false) MultipartFile avatarFile,
            @CurrentUser User user,
            HttpServletRequest request
    ) {
        userMapper.updateFromDTO(userUpdateDTO, user);
        User updatedUser = userService.updateUser(user, avatarFile);
        return userMapper.toUserResponseDTO(updatedUser, request);
    }

    @PutMapping("/me/preferences-vector")
    public UserResponseDTO updatePreferencesVector(@RequestBody int[] preferencesVector,
                                                   @CurrentUser User user,
                                                   HttpServletRequest request) {
        return userMapper.toUserResponseDTO(userService.updatePreferencesVector(user.getId(), preferencesVector), request);
    }

//    @DeleteMapping("/me")
//    public ResponseEntity<Void> deleteUser(@CurrentUser User user) {
//        userService.deleteUser(user.getId());
//        return ResponseEntity.noContent().build();
//    }


}
