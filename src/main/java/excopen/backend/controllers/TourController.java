package excopen.backend.controllers;

import excopen.backend.constants.TourSort;
import excopen.backend.dto.*;
import excopen.backend.entities.Description;
import excopen.backend.entities.Location;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import excopen.backend.iservices.IDescriptionService;
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
    private final IDescriptionService descriptionService;

    @Autowired
    public TourController(ITourService tourService,
                          ILocationService locationService,
                          TourMapper tourMapper,
                          DescriptionMapper descriptionMapper,
                          FileStorageService fileStorageService,
                          ITourImageService tourImageService,
                          TagVectorService tagVectorService, IDescriptionService descriptionService) {
        this.tourService = tourService;
        this.locationService = locationService;
        this.tourMapper = tourMapper;
        this.descriptionMapper = descriptionMapper;
        this.fileStorageService = fileStorageService;
        this.tourImageService = tourImageService;
        this.tagVectorService = tagVectorService;
        this.descriptionService = descriptionService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createTour(
            @Valid @RequestPart("tour") TourCreateDTO tourDTO,
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @CurrentUser User user,
            HttpServletRequest request) {

        Tour tour = tourMapper.toEntity(tourDTO, tagVectorService);
        Tour createdTour = tourService.createTour(tour, user);

        if(images != null && !images.isEmpty()) {
            List<String> imageUrls = images.stream()
                    .map(file -> {
                        try {
                            return fileStorageService.storeTourImage(file);
                        } catch (Exception e) {
                            throw new ResponseStatusException(
                                    HttpStatus.INTERNAL_SERVER_ERROR,
                                    "Failed to upload image: " + file.getOriginalFilename()
                            );
                        }
                    })
                    .toList();
        tourImageService.saveImages(createdTour.getId(), imageUrls);
        }
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
    public void updateTour(
            @PathVariable Long tourId,
            @Valid @RequestPart("tour") TourUpdateDTO updateDTO,
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @CurrentUser User currentUser,
            HttpServletRequest request) {

        Tour existingTour  = tourService.getTourById(tourId);
        tourMapper.updateFromDTO(updateDTO, existingTour, tagVectorService);

        // Сохраняем обновленный тур
         tourService.updateTour(existingTour);

        // Обновляем изображения
        if (images != null && !images.isEmpty()) {
            List<String> imageUrls = images.stream()
                    .map(file -> {
                        try {
                            return fileStorageService.storeTourImage(file);
                        } catch (Exception e) {
                            throw new ResponseStatusException(
                                    HttpStatus.INTERNAL_SERVER_ERROR,
                                    "Failed to upload image: " + file.getOriginalFilename()
                            );
                        }
                    })
                    .toList();
            tourImageService.saveImages(existingTour.getId(), imageUrls);
        }
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

    @GetMapping("/unreviewed/me")
    public List<TourResponseDTO> getVisitedToursWithoutReviews(
            @CurrentUser User user,
            HttpServletRequest request) {
        List<Tour> tours = tourService.getVisitedToursWithoutReviewsByUserId(user.getId());
        return tourMapper.toResponseDTOList(tours, tagVectorService, request);
    }



    @GetMapping("/recommendations/{userId}")
    public List<TourResponseDTO> getRecommendedTours(@PathVariable Long userId,
                                                     HttpServletRequest request) {
        return tourMapper.toResponseDTOList(tourService.getRecommendedTours(userId), tagVectorService, request);
    }

    @GetMapping("/{tourId}/similar")
    public List<TourResponseDTO> getSimilarTours(@PathVariable Long tourId,
                                                 HttpServletRequest request) {
        return tourMapper.toResponseDTOList(tourService.getSimilarTours(tourId), tagVectorService, request);
    }
}
