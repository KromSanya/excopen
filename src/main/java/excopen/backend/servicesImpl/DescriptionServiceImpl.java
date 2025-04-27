package excopen.backend.servicesImpl;

import excopen.backend.entities.Description;
import excopen.backend.entities.Tour;
import excopen.backend.iservices.IDescriptionService;
import excopen.backend.repositories.DescriptionRepository;
import excopen.backend.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DescriptionServiceImpl implements IDescriptionService {

    private final DescriptionRepository descriptionRepository;
    private final TourRepository tourRepository;

    @Autowired
    public DescriptionServiceImpl(DescriptionRepository descriptionRepository,
                                  TourRepository tourRepository) {
        this.descriptionRepository = descriptionRepository;
        this.tourRepository = tourRepository;
    }

    @Override
    @Transactional
    public Description createDescription(Description description, Long tourId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour not found with ID: " + tourId));

        description.setTour(tour);
        return descriptionRepository.save(description);
    }

    @Override
    public Optional<Description> getDescriptionById(Long descriptionId) {
        return descriptionRepository.findById(descriptionId);
    }
	
    @Override
    public Description getDescriptionByTourId(Long tourId) {
        Tour tour = new Tour();
        tour.setId(tourId);
        return descriptionRepository.findByTour(tour)
                .orElseThrow(() -> new IllegalArgumentException("Description not found for tourId: " + tourId));
    }

    @Override
    @Transactional
    public Description updateDescription(Description description) {
        return descriptionRepository.save(description);
    }

    @Override
    @Transactional
    public void deleteDescription(Long descriptionId) {
        descriptionRepository.deleteById(descriptionId);
    }
}
