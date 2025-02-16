package excopen.backend.controllers;

import excopen.backend.dto.TourCreateDTO;
import excopen.backend.dto.TourResponseDTO;
import excopen.backend.dto.TourUpdateDTO;
import excopen.backend.entities.Description;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import excopen.backend.iservices.IDescriptionService;
import excopen.backend.iservices.ITourService;
import excopen.backend.iservices.IUserService;
import excopen.backend.mapper.DescriptionMapper;
import excopen.backend.mapper.TourMapper;
import excopen.backend.security.RequiresOwnership;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tours")
public class TourController {

    private final ITourService tourService;
    private final IDescriptionService descriptionService;
    private final IUserService userService;
    private final TourMapper tourMapper;
    private final DescriptionMapper descriptionMapper;



    @Autowired
    public TourController(ITourService tourService, IDescriptionService descriptionService, IUserService userService, TourMapper tourMapper, DescriptionMapper descriptionMapper) {
        this.tourService = tourService;
        this.descriptionService = descriptionService;
        this.userService = userService;
        this.tourMapper = tourMapper;
        this.descriptionMapper = descriptionMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TourResponseDTO createTour(
            @Valid @RequestBody TourCreateDTO request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        if (jwt == null) {
            throw new IllegalStateException("Пользователь не аутентифицирован");
        }
        String googleId = jwt.getClaim("sub");

        User creator = userService.findByGoogleId(googleId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Tour newTour = tourMapper.toEntity(request);
        tourService.createTour(newTour, creator.getId());

        Description description = descriptionMapper.toEntity(request.getDescription());
        descriptionService.createDescription(description, newTour.getId());

        return tourMapper.toResponseDTO(newTour, description);
    }

    @RequiresOwnership(entityClass = Tour.class)
    @PutMapping("/{tourId}")
    public TourResponseDTO updateTour(
            @PathVariable Long id,
            @Valid @RequestBody TourUpdateDTO updateDTO,
            @AuthenticationPrincipal Jwt jwt
    ) {
        if (!id.equals(updateDTO.getId())) {
            throw new IllegalArgumentException("ID в URL и теле запроса не совпадают");
        }
        Tour newTour = tourMapper.toEntity(updateDTO);
        Tour updatedTour = tourService.updateTour(newTour);

        Description description = descriptionMapper.toEntity(updateDTO.getDescription());
        Description updatedDescription = descriptionService.updateDescription(description);


        return tourMapper.toResponseDTO(updatedTour, updatedDescription);
    }

    @RequiresOwnership(entityClass = Tour.class)
    @DeleteMapping("/{tourId}")
    public void deleteTour(@PathVariable Long tourId) {
        tourService.deleteTour(tourId);
    }

    @GetMapping("/{tourId}")
    public TourResponseDTO getTourById(@PathVariable Long tourId) {
        Tour tour = tourService.getTourById(tourId);
        Description description = descriptionService.getDescriptionByTourId(tourId);
        return tourMapper.toResponseDTO(tour, description);
    }

    @GetMapping
    public List<TourResponseDTO> getAllTours() {
        List<Tour> tours = tourService.getAllTours();
        return tourMapper.toResponseDTOList(tours, descriptionService);
    }

    @GetMapping("/location/{locationId}")
    public List<TourResponseDTO> findToursByLocation(@PathVariable Long locationId) {
        List<Tour> tours = tourService.findToursByLocation(locationId);
        return tourMapper.toResponseDTOList(tours, descriptionService);
    }

    @GetMapping("/duration/{duration}")
    public List<TourResponseDTO> findToursByDuration(@PathVariable String duration) {
        List<Tour> tours = tourService.findToursByDuration(duration);
        return tourMapper.toResponseDTOList(tours, descriptionService);
    }

    @GetMapping("/recommendations/{userId}")
    public List<TourResponseDTO> getRecommendedTours(@PathVariable Long userId) {
        List<Tour> recommendedTours = tourService.getRecommendedTours(userId);
        return tourMapper.toResponseDTOList(recommendedTours, descriptionService);
    }
}
