package excopen.backend.servicesImpl;

import excopen.backend.entities.Review;
import excopen.backend.entities.ReviewImage;
import excopen.backend.iservices.IReviewImageService;
import excopen.backend.repositories.ReviewImageRepository;
import excopen.backend.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewImageServiceImpl implements IReviewImageService {

    private final ReviewImageRepository reviewImageRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewImageServiceImpl(ReviewImageRepository reviewImageRepository, ReviewRepository reviewRepository) {
        this.reviewImageRepository = reviewImageRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewImage addImageToReview(Long reviewId, String imageUrl) {

      reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review with ID " + reviewId + " not found."));

        ReviewImage reviewImage = new ReviewImage();
        reviewImage.setReviewId(reviewId);
        reviewImage.setImageUrl(imageUrl);
        return reviewImageRepository.save(reviewImage);
    }

    @Override
    public void deleteImage(Long reviewImageId) {
        if (!reviewImageRepository.existsById(reviewImageId)) {
            throw new IllegalArgumentException("Review image with ID " + reviewImageId + " does not exist.");
        }
        reviewImageRepository.deleteById(reviewImageId);
    }

    @Override
    public List<ReviewImage> getImagesByReview(Long reviewId) {
        return reviewImageRepository.findByReviewId(reviewId);
    }

    @Override
    public Optional<ReviewImage> getImageById(Long reviewImageId) {
        return reviewImageRepository.findById(reviewImageId);
    }
}



