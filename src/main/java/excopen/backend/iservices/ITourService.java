package excopen.backend.iservices;

import excopen.backend.dto.FilterToursDTO;
import excopen.backend.dto.SearchParamsDTO;
import excopen.backend.dto.TourCreateDTO;
import excopen.backend.dto.TourResponseDTO;
import excopen.backend.entities.Description;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface ITourService {
    Tour createTour(Tour tour, User creator);
    Tour getTourById(Long tourId);
    List<Tour> getToursByCreatorId(Long creatorId);
    List<Tour> getVisitedToursByUserId(Long userId);
    List<Tour> getVisitedToursWithoutReviewsByUserId(Long userId);
    Tour updateTour(Tour tour);
    void deleteTour(Long tourId);
    List<Tour> getAllTours();
    List<Tour> findToursByLocation(Long locationId);
    List<Tour> findToursByDuration(Double duration);
    List<Tour> getRecommendedTours(Long userId);
    List<Tour> getSimilarTours(Long tourId);
    List<Tour> searchTours(SearchParamsDTO params, Sort sort);
    void updateTourStats(Long tourId);
}

