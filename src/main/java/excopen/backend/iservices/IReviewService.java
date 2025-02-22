package excopen.backend.iservices;

import excopen.backend.entities.Review;

import java.util.List;
import java.util.Optional;

public interface IReviewService {
    Review createReview(Review review);
    Review getReviewById(Long reviewId);
    Review updateReview(Review review);
    void deleteReview(Long reviewId);
    List<Review> getReviewsByTour(Long tourId);
    List<Review> getReviewsByUser(Long userId);
    double getAverageRatingForTour(Long tourId);
}
