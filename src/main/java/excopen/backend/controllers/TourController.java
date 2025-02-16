package excopen.backend.controllers;

import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import excopen.backend.iservices.ITourService;
import excopen.backend.iservices.IUserService;
import excopen.backend.security.RequiresOwnership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
public class TourController {

    private final ITourService tourService;
    private final IUserService userService;

    @Autowired
    public TourController(ITourService tourService, IUserService userService) {
        this.tourService = tourService;
        this.userService = userService;
    }

    @PostMapping
    public Tour createTour(@RequestBody Tour tour, @AuthenticationPrincipal OAuth2User principal) {
        String googleId = principal.getAttribute("sub");
        User creator = userService.findByGoogleId(googleId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        tour.setCreator(creator);
        return tourService.createTour(tour);
    }

    @GetMapping("/{tourId}")
    public Tour getTourById(@PathVariable Long tourId) {
        return tourService.getTourById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour not found"));
    }

    @RequiresOwnership(entityClass = Tour.class)
    @PutMapping("/{tourId}")
    public Tour updateTour(@PathVariable Long tourId, @RequestBody Tour tour) {
        Tour existingTour = tourService.getTourById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour not found"));

        tour.setId(tourId);
        tour.setCreator(existingTour.getCreator());
        return tourService.updateTour(tour);
    }

    @RequiresOwnership(entityClass = Tour.class)
    @DeleteMapping("/{tourId}")
    public void deleteTour(@PathVariable Long tourId) {
        tourService.deleteTour(tourId);
    }

    @GetMapping
    public List<Tour> getAllTours() {
        return tourService.getAllTours();
    }
}
