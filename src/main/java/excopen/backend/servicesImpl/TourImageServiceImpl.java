package excopen.backend.servicesImpl;

import excopen.backend.entities.Tour;
import excopen.backend.entities.TourImage;
import excopen.backend.iservices.ITourImageService;
import excopen.backend.repositories.TourImageRepository;
import excopen.backend.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class TourImageServiceImpl implements ITourImageService {

    private final TourImageRepository tourImageRepository;
    private final TourRepository tourRepository;

    @Autowired
    public TourImageServiceImpl(TourImageRepository tourImageRepository, TourRepository tourRepository) {
        this.tourImageRepository = tourImageRepository;
        this.tourRepository = tourRepository;
    }

    @Override
    public TourImage addTourImage(Long tourId, String imageUrl) {
        // Проверка на наличие тура
        tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour with ID " + tourId + " not found."));

        TourImage tourImage = new TourImage();
        tourImage.setTourId(tourId);
        tourImage.setImageUrl(imageUrl);
        return tourImageRepository.save(tourImage);
    }

    @Override
    public void deleteTourImage(Long tourImageId) {
        if (!tourImageRepository.existsById(tourImageId)) {
            throw new IllegalArgumentException("Tour image with ID " + tourImageId + " does not exist.");
        }
        tourImageRepository.deleteById(tourImageId);
    }

    @Override
    public List<TourImage> getImagesByTour(Long tourId) {
        return tourImageRepository.findByTourId(tourId);
    }

    @Override
    public Optional<TourImage> getImageById(Long tourImageId) {
        return tourImageRepository.findById(tourImageId);
    }
}
