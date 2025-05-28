package excopen.backend.servicesImpl;

import com.querydsl.core.BooleanBuilder;
import excopen.backend.dto.FilterToursDTO;
import excopen.backend.dto.SearchParamsDTO;
import excopen.backend.entities.*;
import excopen.backend.iservices.ITourService;
import excopen.backend.repositories.ReviewRepository;
import excopen.backend.repositories.TourRepository;
import org.antlr.v4.runtime.ListTokenSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;

@Service
@Validated
public class TourServiceImpl implements ITourService {

    private final TourRepository tourRepository;
    private final ReviewRepository reviewRepository;
    private final UserServiceImpl userService;
    private final BookingServiceImpl bookingService;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository,
                           ReviewRepository reviewRepository,
                           UserServiceImpl userService, BookingServiceImpl bookingService) {
        this.tourRepository = tourRepository;
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @Transactional
    @Override
    public Tour createTour(Tour tour, User creator) {
//        User creator = userService.getUserById(creatorId);

        tour.setCreator(creator);
//        tour.getDescription().setTour(tour);
        return tourRepository.save(tour);
    }


    @Override
    public Tour getTourById(Long tourId) {
        return tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour not found"));
    }

    @Override
    public List<Tour> getToursByCreatorId(Long creatorId) {
        User creator = userService.getUserById(creatorId);
        return tourRepository.findByCreator(creator);
    }

    @Override
    public List<Tour> getVisitedToursByUserId(Long userId) {
        var bookings = bookingService.getBookingsByUserId(userId);
        List<Tour> tours = new ArrayList<>();
        for(Booking element: bookings)
        {
            tours.add(this.getTourById(element.getTourId()));
        }
        return tours;
    }

    @Override
    public List<Tour> getVisitedToursWithoutReviewsByUserId(Long userId) {
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        List<Tour> toursWithoutReviews = new ArrayList<>();

        for (Booking booking : bookings) {
            Long tourId = booking.getTourId();
            Tour tour = getTourById(tourId);

            if (!reviewRepository.existsByUserIdAndTourId(userId, tourId)) {
                toursWithoutReviews.add(tour);
            }
        }

        return toursWithoutReviews;
    }

    @Override
    public Tour updateTour(Tour tour) {
        return tourRepository.save(tour);
    }



    @Override
    @Transactional
    public void deleteTour(Long tourId) {
        Tour tour = getTourById(tourId);
        tourRepository.delete(tour);
    }

    @Override
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    @Override
    public List<Tour> findToursByLocation(Long locationId) {
        Location location = new Location();
        location.setId(locationId);
        return tourRepository.findByLocation(location);
    }

    @Override
    public List<Tour> findToursByDuration(Double duration) {
        return tourRepository.findByDuration(duration);
    }

    @Override
    public List<Tour> getRecommendedTours(Long userId) {
        int[] preferencesVectorArray = userService.getUserPreferenceVector(userId);
        String preferencesVector = convertArrayToVectorString(preferencesVectorArray);
        return tourRepository.findRecommendedTours(preferencesVector);
    }

    @Override
    public List<Tour> getSimilarTours(Long tourId) {
        Tour baseTour = getTourById(tourId);
        String vectorString = convertArrayToVectorString(baseTour.getVectorRepresentation());
        return tourRepository.findSimilarTours(tourId, vectorString);
    }

    @Override
    public List<Tour> searchTours(SearchParamsDTO params, Sort sort) {
        QTour tour = QTour.tour;
        BooleanBuilder predicate = new BooleanBuilder();

        // Поиск по городу и региону
        if (params.getCity() != null) {
            predicate.and(tour.location.city.equalsIgnoreCase(params.getCity()));
        }
        if (params.getRegion() != null) {
            predicate.and(tour.location.region.equalsIgnoreCase(params.getRegion()));
        }

        // Поиск по диапазону дат
        if (params.getFrom() != null) {
            predicate.and(tour.date.goe(params.getFrom()));
        }
        if (params.getTo() != null) {
            predicate.and(tour.date.loe(params.getTo()));
        }

        // Остальные фильтры
        if (params.getAccessibility() != null) {
            predicate.and(tour.accessibility.stringValue().eq(params.getAccessibility()));
        }
        if (params.getByCity() != null) {
            predicate.and(tour.byCity.eq(params.getByCity()));
        }

        return (List<Tour>) tourRepository.findAll(predicate, sort);
    }

    @Override
    @Transactional
    public void updateTourStats(Long tourId) {
        Tour tour = getTourById(tourId);

        List<Review> reviews = reviewRepository.findByTour(tour);

        double averageRating = reviews.stream()
                .map(Review::getRating)
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(Double.NaN);

        int reviewCount = reviews.size();

        if (Double.isNaN(averageRating)) {
            tour.setRating(null);
        } else {
            double rounded = Math.round(averageRating * 10.0) / 10.0;
            tour.setRating(rounded);
        }

        tour.setReviewCount(reviewCount);
        tourRepository.save(tour);
    }

    private String convertArrayToVectorString(int[] array) {
        return "[" + Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", ")) + "]";
    }
}
