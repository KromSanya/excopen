package excopen.backend.controllers;

import excopen.backend.dto.TourResponseDTO;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import excopen.backend.iservices.IFavoriteService;
import excopen.backend.mapper.TourMapper;
import excopen.backend.security.CurrentUser;
import excopen.backend.servicesImpl.TagVectorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final IFavoriteService favoriteService;
    private final TourMapper tourMapper;
    private final TagVectorService tagVectorService;

    @Autowired
    public FavoriteController(IFavoriteService favoriteService,
                              TourMapper tourMapper, TagVectorService tagVectorService) {
        this.favoriteService = favoriteService;
        this.tourMapper = tourMapper;
        this.tagVectorService = tagVectorService;
    }

    @PostMapping("/{tourId}")
    public void addTourToFavorites(@PathVariable Long tourId, @CurrentUser User user) {
        favoriteService.addTourToFavorites(user.getId(), tourId);
    }

    @DeleteMapping("/{tourId}")
    public void removeTourFromFavorites(@PathVariable Long tourId, @CurrentUser User user) {
        favoriteService.removeTourFromFavorites(user.getId(), tourId);
    }

    @GetMapping
    public List<TourResponseDTO> getFavoriteToursByUser(
            @CurrentUser User user,
            HttpServletRequest request
    ) {
        List<Tour> tours = favoriteService.getFavoriteToursByUser(user.getId());
        return tourMapper.toResponseDTOList(tours, tagVectorService, request);
    }
}
