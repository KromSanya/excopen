package excopen.backend.controllers;

import excopen.backend.constants.TourAccessibility;
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

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
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

        logger.info("Creating tour for user: {}", user.getId());

        // Валидация изображений
        if (images == null || images.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "At least one image is required");
        }

        Location location = locationService.getLocationById(tourDTO.getLocation().getId());
        Tour tour = tourMapper.toEntity(tourDTO, location, tagVectorService);

        Description description = descriptionMapper.toEntity(tourDTO.getDescription());
        tour.setDescription(description);

        Tour createdTour = tourService.createTour(tour, user.getId());

        // Сохранение изображений
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


//    /**
//     * Тестовый метод создания тура без авторизации (пушим DTO напрямую).
//     */
//    @PostMapping(path = "/test", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @ResponseStatus(HttpStatus.CREATED)
//    public TourResponseDTO createTourTest(
//            @Valid @ModelAttribute("tour") TourCreateDTO tourDTO, // Исправлено
//            @RequestPart("images") List<MultipartFile> images // Исправлено
//    ) {
//        // 2) Получаем привязку к локации
//        Location location = locationService.getLocationById(tourDTO.getLocation().getId());
//
//        // 3) Маппим в сущность (в том числе coordinates и contacts автоматически)
//        Tour tour = tourMapper.toEntity(tourDTO, location, tagVectorService);
//
//        // 4) Описания
//        Description description = descriptionMapper.toEntity(tourDTO.getDescription());
//        tour.setDescription(description);
//
//        // 5) Сохраняем тур (жёстко передаём userId=2L для теста)
//        Tour created = tourService.createTour(tour, 2L);
//
//        // 6) Сохраняем картинки уже после того, как тур создан
//        for (MultipartFile img : images) {
//            String url = fileStorageService.storeTourImage(img);
//            tourImageService.addTourImage(created.getId(), url);
//        }
//
//        // 7) Возвращаем готовый ответ
//        return tourMapper.toResponseDTO(created, tagVectorService);
//    }


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


    /**
     * Тестовый метод, который просто собирает и возвращает
     * «пустой» шаблон TourResponseDTO с примерными значениями.
     */
    @GetMapping("/template")
    public ResponseEntity<TourResponseDTO> getTourTemplate() {
        TourResponseDTO dto = new TourResponseDTO();

        // ID-шник шаблона
        dto.setId(0L);

        // Базовые поля
        dto.setTitle("Ночная прогулка по Санкт-Петербургу");
        dto.setRouteLength(3.8);
        dto.setByCity(true);
        dto.setPrice(1800);
        dto.setGroupCapacity(12);
        dto.setDuration(2.5);
        dto.setContributorId(123L);
        dto.setRating(4.9);
        dto.setRatingCount(76);

        // Форматы
        dto.setFormat("Груповой");
        dto.setFormatBehavior("Пешком");
        dto.setAccessibility(TourAccessibility.with_children);

        // Дата и время
        dto.setDate(LocalDate.of(2025, 5, 20));
        dto.setTime(LocalTime.of(20, 0));

        // Координаты старта (пример Яндекс-карт)
        CoordinateDTO coords = new CoordinateDTO();
        coords.getPoint().setLongitude(59.9386);
        coords.getPoint().setLatitude(30.3141);
        coords.setZoom(14.0);
        dto.setCoordinates(coords);

        // Теги
        dto.setTags(List.of("Исторический", "Семейный", "Экстрим"));

        // Описание
        DescriptionResponseDTO desc = new DescriptionResponseDTO();
        desc.setInfo("Прогулка по набережным, разводные мосты, огни ночного города.");
        desc.setWhatToExpect("Небольшая пешая экскурсия по красивейшим местам города ночью.");
        desc.setOrgDetails("Экскурсия проводится ежедневно, сбор у Адмиралтейства.");
        desc.setMeetingPlace("Санкт-Петербург, Адмиралтейская набережная, д. 2");
        desc.setPlaces(List.of("Дворцовая площадь", "Невский проспект"));
        desc.setTopics(List.of("История", "Архитектура"));
        dto.setDescription(desc);

        // Изображения
        dto.setImages(List.of(
                "https://lh3.googleusercontent.com/a/ACg8ocIphDkU5pbj15e0QBKgDZEuXvFs9A3QBd4LnswlWBtRtwFghw=s96-c",
                "https://lh3.googleusercontent.com/a/ACg8ocIphDkU5pbj15e0QBKgDZEuXvFs9A3QBd4LnswlWBtRtwFghw=s96-c"
        ));

        ContactDTO contacts = new ContactDTO();
        contacts.setVk("vk.com/guide");
        contacts.setTelegram("@guide_bot");
        contacts.setPhone("+7 (900) 123-45-67");
        dto.setContacts(contacts);

        // Локация
        LocationDTO locResp = new LocationDTO(
                1L,
                "Санкт-Петербург",
                "Ленинградская область",
                "Россия",
                "https://example.com/locations/spb.jpg",
                125L
        );

        CoordinateDTO coord = new CoordinateDTO(

        );
        dto.setLocation(locResp);

        return ResponseEntity.ok(dto);
    }



//    @GetMapping
//    public List<TourResponseDTO> getAllTours() {
//        return tourMapper.toResponseDTOList(tourService.getAllTours(), tagVectorService);
//    }
//
//    @GetMapping("/location/{locationId}")
//    public List<TourResponseDTO> findToursByLocation(@PathVariable Long locationId) {
//        return tourMapper.toResponseDTOList(tourService.findToursByLocation(locationId), tagVectorService);
//    }
//
//    @GetMapping("/duration/{duration}")
//    public List<TourResponseDTO> findToursByDuration(@PathVariable Double duration) {
//        return tourMapper.toResponseDTOList(tourService.findToursByDuration(duration), tagVectorService);
//    }
//
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
