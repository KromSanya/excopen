package excopen.backend.controllers;

import excopen.backend.dto.TourResponseDTO;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import excopen.backend.iservices.IDescriptionService;
import excopen.backend.iservices.IFavoriteService;
import excopen.backend.iservices.IUserService;
import excopen.backend.mapper.TourMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final IFavoriteService favoriteService;
    private final IUserService userService;
    private final IDescriptionService descriptionService;
    private final TourMapper tourMapper;

    @Autowired
    public FavoriteController(IFavoriteService favoriteService,
                              IUserService userService,
                              IDescriptionService descriptionService,
                              TourMapper tourMapper) {
        this.favoriteService = favoriteService;
        this.userService = userService;
        this.descriptionService = descriptionService;
        this.tourMapper = tourMapper;
    }

    @PostMapping("/{tourId}")
    public void addTourToFavorites(@PathVariable Long tourId,
                                   @AuthenticationPrincipal OAuth2User principal) {
        String googleId = principal.getAttribute("sub");
        User user = userService.getUserByGoogleId(googleId);
        favoriteService.addTourToFavorites(user.getId(), tourId);
    }

    @DeleteMapping("/{tourId}")
    public void removeTourFromFavorites(@PathVariable Long tourId,
                                        @AuthenticationPrincipal OAuth2User principal) {
        String googleId = principal.getAttribute("sub");
        User user = userService.getUserByGoogleId(googleId);
        favoriteService.removeTourFromFavorites(user.getId(), tourId);
    }

    @GetMapping
    public List<TourResponseDTO> getFavoriteToursByUser(@AuthenticationPrincipal OAuth2User principal) {
        String googleId = principal.getAttribute("sub");
        User user = userService.getUserByGoogleId(googleId);
        List<Tour> tours = favoriteService.getFavoriteToursByUser(user.getId());
        return tourMapper.toResponseDTOList(tours, descriptionService);
    }
}