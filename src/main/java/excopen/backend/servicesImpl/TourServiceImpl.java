package excopen.backend.servicesImpl;

import excopen.backend.entities.Tour;
import excopen.backend.iservices.ITourService;
import excopen.backend.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TourServiceImpl implements ITourService {

    private final TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public Tour createTour(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public Optional<Tour> getTourById(Long tourId) {
        return tourRepository.findById(tourId);
    }

    @Override
    public Tour updateTour(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public void deleteTour(Long tourId) {
        tourRepository.deleteById(tourId);
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
    public List<Tour> findToursByDuration(String duration) {
        return tourRepository.findByDuration(duration);
    }
}
