package excopen.backend.controllers;

import excopen.backend.dto.UserResponseDTO;
import excopen.backend.dto.UserUpdateDTO;
import excopen.backend.entities.User;
import excopen.backend.iservices.IUserService;
import excopen.backend.mapper.UserMapper;
import excopen.backend.security.RequiresUserAuthorization;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/test")
    public String testRoute() {
        return "Контроллер работает!";
    }

    @GetMapping("/{userId}")
    @RequiresUserAuthorization
    public UserResponseDTO getUserById(
            @PathVariable Long userId,
            @AuthenticationPrincipal OAuth2User principal) {
        User user = userService.getUserById(userId);
        return userMapper.toResponseDTO(user);
    }

    @GetMapping("/me")
    public UserResponseDTO getCurrentUser(@AuthenticationPrincipal OAuth2User principal) {
        String googleId = principal.getAttribute("sub");
        User user = userService.getUserByGoogleId(googleId);
        return userMapper.toResponseDTO(user);
    }

    /// Только для разработки
    @GetMapping("/attributes")
    public Map<String, Object> userAttributes(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes();
    }

    @RequiresUserAuthorization
    @PutMapping("/{userId}")
    public UserResponseDTO updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UserUpdateDTO userUpdateDTO,
            @AuthenticationPrincipal OAuth2User principal) {
        User existingUser = userService.getUserById(userId);
        userMapper.updateFromDTO(userUpdateDTO, existingUser);
        User updatedUser = userService.updateUser(userId, existingUser);
        return userMapper.toResponseDTO(updatedUser);
    }

    @RequiresUserAuthorization
    @PutMapping("/{userId}/preferences-vector")
    public UserResponseDTO updatePreferencesVector(
            @PathVariable Long userId,
            @RequestBody int[] preferencesVector,
            @AuthenticationPrincipal OAuth2User principal) {
        User updatedUser = userService.updatePreferencesVector(userId, preferencesVector);
        return userMapper.toResponseDTO(updatedUser);
    }

    @RequiresUserAuthorization
    @DeleteMapping("/{userId}")
    public void deleteUser(
            @PathVariable Long userId,
            @AuthenticationPrincipal OAuth2User principal) {
        userService.deleteUser(userId);
    }
}
