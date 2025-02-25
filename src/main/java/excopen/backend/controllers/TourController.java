package excopen.backend.controllers;

import excopen.backend.dto.TourDTO;
import excopen.backend.entities.Description;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import excopen.backend.iservices.IDescriptionService;
import excopen.backend.iservices.ITourService;
import excopen.backend.iservices.IUserService;
import excopen.backend.security.RequiresOwnership;
import excopen.backend.servicesImpl.DescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
public class TourController {

    private final ITourService tourService;
    private final IDescriptionService descriptionService;
    @Autowired
    public TourController(ITourService tourService, IDescriptionService descriptionService) {
        this.tourService = tourService;
        this.descriptionService = descriptionService;
    }

    @PostMapping
    public ResponseEntity<TourDTO> createTour(@RequestBody TourDTO request,
                                           @AuthenticationPrincipal Jwt jwt) {
        if (jwt == null) {
            throw new IllegalStateException("User is not authenticated");
        }
        String googleId = jwt.getClaim("sub");

        Tour createdTour = tourService.createTour(request.getTour(), googleId);

        Description description = request.getDescription();
        description.setTourId(createdTour.getId());
        descriptionService.createDescription(description);

        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }





    @RequiresOwnership(entityClass = Tour.class)
    @PutMapping("/{tourId}")
    public Tour updateTour(@PathVariable Long tourId, @RequestBody Tour tour, @RequestBody Description description) {
        return tourService.updateTour(tourId, tour, description);
    }

    @RequiresOwnership(entityClass = Tour.class)
    @DeleteMapping("/{tourId}")
    public void deleteTour(@PathVariable Long tourId) {
        tourService.deleteTour(tourId);
    }

    @GetMapping("/{tourId}")
    public TourDTO getTourById(@PathVariable Long tourId) {
        TourDTO tourDTO = new TourDTO();
        tourDTO.setTour(tourService.getTourById(tourId));
        tourDTO.setDescription(descriptionService.getDescriptionByTourId(tourId));
        return tourDTO;
    }


    @GetMapping
    public List<Tour> getAllTours() {
        return tourService.getAllTours();
    }

    @GetMapping("/location/{locationId}")
    public List<Tour> findToursByLocation(@PathVariable Long locationId) {
        return tourService.findToursByLocation(locationId);
    }

    @GetMapping("/duration/{duration}")
    public List<Tour> findToursByDuration(@PathVariable String duration) {
        return tourService.findToursByDuration(duration);
    }

    @GetMapping("/recommendations/{userId}")
    public List<TourDTO> getRecommendedTours(@PathVariable Long userId) {
        return tourService.getRecommendedTours(userId);
    }

}
