package excopen.backend.controllers;

import excopen.backend.entities.User;
import excopen.backend.iservices.IUserService;
import excopen.backend.security.RequiresUserAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String testRoute() {
        return "Контроллер работает!";
    }

    @GetMapping("/{userId}")
    @RequiresUserAuthorization
    public User getUserById(
            @PathVariable Long userId,
            @AuthenticationPrincipal OAuth2User principal) {
        return userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @GetMapping("/me")
    public User getCurrentUser(@AuthenticationPrincipal OAuth2User principal) {
        String googleId = principal.getAttribute("sub");
        return userService.findByGoogleId(googleId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @RequiresUserAuthorization
    @PutMapping("/{userId}")
    public User updateUser(
            @PathVariable Long userId,
            @RequestBody User user,
            @AuthenticationPrincipal OAuth2User principal) {
        User existingUser = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setId(userId);
        user.setGoogleId(existingUser.getGoogleId());
        return userService.updateUser(userId, user);
    }

    @RequiresUserAuthorization
    @DeleteMapping("/{userId}")
    public void deleteUser(
            @PathVariable Long userId,
            @AuthenticationPrincipal OAuth2User principal) {
        userService.deleteUser(userId);
    }

    @GetMapping("/attributes")
    public Map<String, Object> userAttributes(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes();
    }

    @RequiresUserAuthorization
    @PutMapping("/{userId}/preferences-vector")
    public User updatePreferencesVector(
            @PathVariable Long userId,
            @RequestBody float[] preferencesVector,
            @AuthenticationPrincipal OAuth2User principal) {
        return userService.updatePreferencesVector(userId, preferencesVector);
    }
}
