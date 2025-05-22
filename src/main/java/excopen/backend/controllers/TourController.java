package excopen.backend.controllers;

import excopen.backend.constants.TourSort;
import excopen.backend.dto.*;
import excopen.backend.entities.Description;
import excopen.backend.entities.Location;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import excopen.backend.iservices.ILocationService;
import excopen.backend.iservices.ITourImageService;
import excopen.backend.iservices.ITourService;
import excopen.backend.mapper.DescriptionMapper;
import excopen.backend.mapper.TourMapper;
import excopen.backend.security.RequiresOwnership;
import excopen.backend.security.CurrentUser;
import excopen.backend.servicesImpl.FileStorageService;
import excopen.backend.servicesImpl.TagVectorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/tours")
public class TourController {

    private static final Logger logger = LoggerFactory.getLogger(TourController.class);


    private final ITourService tourService;
    private final ILocationService locationService;
    private final TourMapper tourMapper;
    private final DescriptionMapper descriptionMapper;
    private final FileStorageService fileStorageService;
    private final ITourImageService tourImageService;
    private final TagVectorService tagVectorService;

    @Autowired
    public TourController(ITourService tourService,
                          ILocationService locationService,
                          TourMapper tourMapper,
                          DescriptionMapper descriptionMapper,
                          FileStorageService fileStorageService,
                          ITourImageService tourImageService,
                          TagVectorService tagVectorService) {
        this.tourService = tourService;
        this.locationService = locationService;
        this.tourMapper = tourMapper;
        this.descriptionMapper = descriptionMapper;
        this.fileStorageService = fileStorageService;
        this.tourImageService = tourImageService;
        this.tagVectorService = tagVectorService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createTour(
            @Valid @RequestPart("tour") TourCreateDTO tourDTO,
            @RequestPart("images") List<MultipartFile> images,
            @CurrentUser User user,
            HttpServletRequest request) {
        if (images == null || images.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "At least one image is required");
        }

        Location location = locationService.getLocationById(tourDTO.getLocation().getId());
        Tour tour = tourMapper.toEntity(tourDTO, location, tagVectorService);

        Description description = descriptionMapper.toEntity(tourDTO.getDescription());
        tour.setDescription(description);

        Tour createdTour = tourService.createTour(tour, user.getId());

        List<String> imageUrls = images.stream()
                .map(file -> {
                    try {
                        return fileStorageService.storeTourImage(file);
                    } catch (Exception e) {
                        logger.error("Failed to store image: {}", e.getMessage());
                        throw new ResponseStatusException(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Failed to upload image: " + file.getOriginalFilename()
                        );
                    }
                })
                .toList();

        tourImageService.saveImages(createdTour.getId(), imageUrls);

//        return ResponseEntity
//                .created(URI.create("/api/tours/" + createdTour.getId()))
//                .body(tourMapper.toResponseDTO(createdTour, tagVectorService, request));
    }

    @GetMapping
    public ResponseEntity<List<TourResponseDTO>> searchTours(
            @ModelAttribute SearchParamsDTO searchParams,
            @RequestParam(name = "_sort", defaultValue = "FOR_POPULAR") String sortStrategy,
            HttpServletRequest request) {

        try {
            Sort sort = TourSort.parseSort(sortStrategy);
            List<Tour> tours = tourService.searchTours(searchParams, sort);

            List<TourResponseDTO> response = tours.stream()
                    .map(t -> tourMapper.toResponseDTO(t, tagVectorService, request))
                    .toList();

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            logger.warn("Invalid sort parameter: {}", sortStrategy);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sort parameter");
        }
    }


    @RequiresOwnership(entityClass = Tour.class)
    @PutMapping("/{tourId}")
    public TourResponseDTO updateTour(
            @PathVariable Long tourId,
            @Valid @RequestBody TourUpdateDTO updateDTO,
            HttpServletRequest request) {

        Location location = updateDTO.getLocationId() != null
                ? locationService.getLocationById(updateDTO.getLocationId())
                : null;

        Tour tour = tourMapper.toEntity(updateDTO, location, tagVectorService); // FIXED
        tour.setId(tourId);

        if (updateDTO.getDescription() != null) {
            Description description = descriptionMapper.toEntity(updateDTO.getDescription());
            tour.setDescription(description);
        }

        Tour updatedTour = tourService.updateTour(tour);
        return tourMapper.toResponseDTO(updatedTour, tagVectorService, request);
    }

    @RequiresOwnership(entityClass = Tour.class)
    @DeleteMapping("/{tourId:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTour(@PathVariable Long tourId) {
        tourService.deleteTour(tourId);
    }

    @GetMapping("/{tourId:\\d+}")
    public TourResponseDTO getTourById(
            @PathVariable Long tourId,
            HttpServletRequest request
    ) {
        Tour tour = tourService.getTourById(tourId);
        return tourMapper.toResponseDTO(tour, tagVectorService, request);
    }

    @GetMapping("/guide/{guideId}")
    public List<TourResponseDTO> getToursByGuideId(@PathVariable Long guideId, HttpServletRequest request) {
        return tourMapper.toResponseDTOList(tourService.getToursByCreatorId(guideId), tagVectorService, request);
    }

    @GetMapping("/bookings/me")
    public List<TourResponseDTO> getVisitedToursByUserId( @CurrentUser User user, HttpServletRequest request) {
        return tourMapper.toResponseDTOList(tourService.getVisitedToursByUserId(user.getId()), tagVectorService, request);
    }



//    @GetMapping("/recommendations/{userId}")
//    public List<TourResponseDTO> getRecommendedTours(@PathVariable Long userId) {
//        return tourMapper.toResponseDTOList(tourService.getRecommendedTours(userId), tagVectorService);
//    }
//
//    @GetMapping("/{tourId}/similar")
//    public List<TourResponseDTO> getSimilarTours(@PathVariable Long tourId) {
//        return tourMapper.toResponseDTOList(tourService.getSimilarTours(tourId), tagVectorService);
//    }
}
