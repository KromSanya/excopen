package excopen.backend.servicesImpl;

import excopen.backend.dto.FilterToursDTO;
import excopen.backend.entities.Tour;
import excopen.backend.iservices.ITourService;
import excopen.backend.repositories.TourRepository;
import excopen.backend.specifications.TourSpecifications;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class TourServiceImpl implements ITourService {

    private final TourRepository tourRepository;
    private final UserServiceImpl userService;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository,
                           UserServiceImpl userService) {
        this.tourRepository = tourRepository;
        this.userService = userService;
    }

    @Transactional
    public Tour createTour(Tour tour, Long creatorId) {
        Tour savedTour = tourRepository.save(tour);
        savedTour.setCreatorId(creatorId);
        return savedTour;
    }


    @Override
    public Tour getTourById(Long tourId) {
        return tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour not found"));
    }


    @Override
    public Tour updateTour(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public void deleteTour(Long tourId) {
        Tour existingTour = getTourById(tourId);
        tourRepository.delete(existingTour);
    }

    @Override
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    @Override
    public List<Tour> findToursByLocation(Long locationId) {
        return tourRepository.findByLocationId(locationId);
    }

    @Override
    public List<Tour> findToursByDuration(BigDecimal duration) {
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
        Tour baseTour = this.getTourById(tourId);
        String vectorString = convertArrayToVectorString(baseTour.getVectorRepresentation());
        return tourRepository.findSimilarTours(tourId, vectorString);
    }

    @Override
    public List<Tour> filterTours(FilterToursDTO filter) {
        var spec = TourSpecifications.buildFilterSpec(filter);

        Sort sort = Sort.unsorted();
        if (filter.getSortBy() != null && !filter.getSortBy().trim().isEmpty()) {
            Sort.Direction direction = "DESC".equalsIgnoreCase(filter.getSortOrder())
                    ? Sort.Direction.DESC
                    : Sort.Direction.ASC;
            sort = Sort.by(direction, filter.getSortBy());
        }
        return tourRepository.findAll(spec, sort);
    }

    private String convertArrayToVectorString(int[] array) {
        return "[" + Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", ")) + "]";
    }
}
