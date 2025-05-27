package excopen.backend.iservices;

import excopen.backend.entities.Review;
import excopen.backend.entities.User;

import java.util.List;
import java.util.Optional;

public interface IReviewService {
    Review createReview(Review review);
    Review getReviewById(Long reviewId);
    Review updateReview(Review review);
    void deleteReview(Long reviewId);
    List<Review> getReviewsByTour(Long tourId);
    List<Review> getReviewsByUser(Long userId);
    Double getAverageRatingByCreatorId(Long creatorId);
    Integer getReviewCountByCreatorId(Long creatorId);
    Boolean checkReviewExists(Long userId, Long tourId);
//    double getAverageRatingForTour(Long tourId);
}
