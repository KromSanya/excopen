package excopen.backend.controllers;

import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import excopen.backend.iservices.IFavoriteService;
import excopen.backend.iservices.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final IFavoriteService favoriteService;
    private final IUserService userService;

    @Autowired
    public FavoriteController(IFavoriteService favoriteService, IUserService userService) {
        this.favoriteService = favoriteService;
        this.userService = userService;
    }

    @PostMapping("/{tourId}")
    public void addTourToFavorites(@PathVariable Long tourId, @AuthenticationPrincipal OAuth2User principal) {
        String googleId = principal.getAttribute("sub");
        User user = userService.findByGoogleId(googleId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        favoriteService.addTourToFavorites(user.getId(), tourId);
    }

    @DeleteMapping("/{tourId}")
    public void removeTourFromFavorites(@PathVariable Long tourId, @AuthenticationPrincipal OAuth2User principal) {
        String googleId = principal.getAttribute("sub");
        User user = userService.findByGoogleId(googleId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        favoriteService.removeTourFromFavorites(user.getId(), tourId);
    }

    @GetMapping
    public List<Tour> getFavoriteToursByUser(@AuthenticationPrincipal OAuth2User principal) {
        String googleId = principal.getAttribute("sub");
        User user = userService.findByGoogleId(googleId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return favoriteService.getFavoriteToursByUser(user.getId());
    }
}
