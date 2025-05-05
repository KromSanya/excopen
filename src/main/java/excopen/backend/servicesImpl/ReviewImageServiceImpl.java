//package excopen.backend.servicesImpl;
//
//import excopen.backend.entities.Review;
//import excopen.backend.entities.ReviewImage;
//import excopen.backend.entities.Tour;
//import excopen.backend.entities.TourImage;
//import excopen.backend.iservices.IReviewImageService;
//import excopen.backend.repositories.ReviewImageRepository;
//import excopen.backend.repositories.ReviewRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ReviewImageServiceImpl implements IReviewImageService {
//
//    private final ReviewImageRepository reviewImageRepository;
//    private final ReviewRepository reviewRepository;
//
//    @Autowired
//    public ReviewImageServiceImpl(ReviewImageRepository reviewImageRepository, ReviewRepository reviewRepository) {
//        this.reviewImageRepository = reviewImageRepository;
//        this.reviewRepository = reviewRepository;
//    }
//
//    @Override
//    @Transactional
//    public void saveImages(Long reviewId, List<String> imageUrls) {
//        Review review = reviewRepository.findById(reviewId)
//                .orElseThrow(() -> new IllegalArgumentException("Review with ID " + reviewId + " not found."));
//
//        for (String url : imageUrls) {
//            ReviewImage image = new ReviewImage();
//            image.setReview(review);
//            image.setImageUrl(url);
//            reviewImageRepository.save(image);
//        }
//    }
//
//    @Override
//    @Transactional
//    public ReviewImage addReviewImage(Long reviewId, String imageUrl) {
//        Review review = reviewRepository.findById(reviewId)
//                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
//
//        ReviewImage image = new ReviewImage();
//        image.setReview(review);
//        image.setImageUrl(imageUrl);
//
//        return reviewImageRepository.save(image);
//    }
//
//    @Override
//    @Transactional
//    public void deleteImage(Long reviewImageId) {
//        if (!reviewImageRepository.existsById(reviewImageId)) {
//            throw new IllegalArgumentException("Review image with ID " + reviewImageId + " does not exist.");
//        }
//        reviewImageRepository.deleteById(reviewImageId);
//    }
//
//    @Override
//    public List<ReviewImage> getImagesByReview(Long reviewId) {
//        Review review = reviewRepository.findById(reviewId)
//                .orElseThrow(() -> new IllegalArgumentException("Review with ID " + reviewId + " not found."));
//        return reviewImageRepository.findByReview(review);
//    }
//
//    @Override
//    public Optional<ReviewImage> getImageById(Long reviewImageId) {
//        return reviewImageRepository.findById(reviewImageId);
//    }
//}
