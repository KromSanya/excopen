package excopen.backend.iservices;

import excopen.backend.entities.Tour;

import java.util.List;
import java.util.Optional;


public interface ITourService {
    Tour createTour(Tour tour);
    Optional<Tour> getTourById(Long tourId);
    Tour updateTour(Tour tour);
    void deleteTour(Long tourId);
    List<Tour> getAllTours();
    List<Tour> findToursByLocation(Long locationId);
    //List<Tour> findToursByTags(List<Long> tagIds);
    List<Tour> findToursByDuration(String duration);

}

