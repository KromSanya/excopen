package excopen.backend.iservices;

import excopen.backend.dto.TourCreateDTO;
import excopen.backend.dto.TourResponseDTO;
import excopen.backend.entities.Description;
import excopen.backend.entities.Tour;

import java.util.List;
import java.util.Optional;


public interface ITourService {
    Tour createTour(Tour tour, Long creatorId);
    Tour getTourById(Long tourId);
    public Tour updateTour(Tour tour);
    void deleteTour(Long tourId);
    List<Tour> getAllTours();
    List<Tour> findToursByLocation(Long locationId);
    List<Tour> findToursByDuration(String duration);
    public List<Tour> getRecommendedTours(Long userId);
    List<Tour> getSimilarTours(Long tourId);
}

