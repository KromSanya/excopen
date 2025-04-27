package excopen.backend.servicesImpl;

import com.querydsl.core.BooleanBuilder;
import excopen.backend.dto.FilterToursDTO;
import excopen.backend.entities.*;
import excopen.backend.iservices.ITourService;
import excopen.backend.repositories.ReviewRepository;
import excopen.backend.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;

@Service
@Validated
public class TourServiceImpl implements ITourService {

    private final TourRepository tourRepository;
    private final ReviewRepository reviewRepository;
    private final UserServiceImpl userService;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository,
                           ReviewRepository reviewRepository,
                           UserServiceImpl userService) {
        this.tourRepository = tourRepository;
        this.reviewRepository = reviewRepository;
        this.userService = userService;
    }

    @Transactional
    public Tour createTour(Tour tour, Long creatorId) {
        User creator = userService.getUserById(creatorId);

        tour.setCreator(creator);
        tour.getDescription().setTour(tour);

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
    public Page<Tour> filterTours(FilterToursDTO filter, Pageable pageable) {
        QTour tour = QTour.tour;
        BooleanBuilder predicate = new BooleanBuilder();

        if (filter.getTitle() != null) {
            predicate.and(tour.title.containsIgnoreCase(filter.getTitle()));
        }
        if (filter.getLocationId() != null) {
            predicate.and(tour.location.id.eq(filter.getLocationId()));
        }
        if (filter.getPriceFrom() != null) {
            predicate.and(tour.price.goe(filter.getPriceFrom()));
        }
        if (filter.getPriceTo() != null) {
            predicate.and(tour.price.loe(filter.getPriceTo()));
        }
        if (filter.getDurationFrom() != null) {
            predicate.and(tour.duration.goe(filter.getDurationFrom()));
        }
        if (filter.getDurationTo() != null) {
            predicate.and(tour.duration.loe(filter.getDurationTo()));
        }
        if (filter.getRouteLengthFrom() != null) {
            predicate.and(tour.routeLength.goe(filter.getRouteLengthFrom()));
        }
        if (filter.getRouteLengthTo() != null) {
            predicate.and(tour.routeLength.loe(filter.getRouteLengthTo()));
        }
        if (filter.getRatingFrom() != null) {
            predicate.and(tour.rating.goe(filter.getRatingFrom()));
        }
        if (filter.getRatingTo() != null) {
            predicate.and(tour.rating.loe(filter.getRatingTo()));
        }
        if (filter.getTourType() != null) {
            predicate.and(tour.tourType.eq(filter.getTourType()));
        }
        if (filter.getTransportType() != null) {
            predicate.and(tour.transportType.eq(filter.getTransportType()));
        }
        if (filter.getMinAge() != null) {
            predicate.and(tour.minAge.loe(filter.getMinAge()));
        }
        if (filter.getCapacity() != null) {
            predicate.and(tour.maxCapacity.goe(filter.getCapacity()));
        }

        return tourRepository.findAll(predicate, pageable);
    }

    @Transactional
    public void updateTourStats(Long tourId) {
        Tour tour = getTourById(tourId);

        List<Review> reviews = reviewRepository.findByTour(tour);

        Double averageRating = reviews.stream()
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
