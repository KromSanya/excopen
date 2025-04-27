package excopen.backend.servicesImpl;

import excopen.backend.entities.Tour;
import excopen.backend.entities.TourImage;
import excopen.backend.iservices.ITourImageService;
import excopen.backend.repositories.TourImageRepository;
import excopen.backend.repositories.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TourImageServiceImpl implements ITourImageService {

    private final TourImageRepository tourImageRepository;
    private final TourRepository tourRepository;

    @Override
    @Transactional
    public void saveImages(Long tourId, List<String> imageUrls) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour with ID " + tourId + " not found."));

        for (String url : imageUrls) {
            TourImage image = new TourImage();
            image.setTour(tour);
            image.setImageUrl(url);
            tourImageRepository.save(image);
        }
    }

    @Override
    @Transactional
    public TourImage addTourImage(Long tourId, String imageUrl) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour with ID " + tourId + " not found."));

        TourImage tourImage = new TourImage();
        tourImage.setTour(tour);
        tourImage.setImageUrl(imageUrl);
        return tourImageRepository.save(tourImage);
    }

    @Override
    @Transactional
    public void deleteTourImage(Long tourImageId) {
        if (!tourImageRepository.existsById(tourImageId)) {
            throw new IllegalArgumentException("Tour image with ID " + tourImageId + " does not exist.");
        }
        tourImageRepository.deleteById(tourImageId);
    }

    @Override
    @Transactional
    public List<TourImage> getImagesByTour(Long tourId) {
        return tourImageRepository.findByTourId(tourId);
    }

    @Override
    @Transactional
    public Optional<TourImage> getImageById(Long tourImageId) {
        return tourImageRepository.findById(tourImageId);
    }
}
