package excopen.backend.servicesImpl;

import excopen.backend.entities.Review;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import excopen.backend.events.ReviewCreatedEvent;
import excopen.backend.exceptions.DuplicateReviewException;
import excopen.backend.iservices.IReviewService;
import excopen.backend.iservices.IUserService;
import excopen.backend.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ReviewServiceImpl implements IReviewService {

    private final ReviewRepository reviewRepository;
    private final TourServiceImpl tourService;
    private final IUserService userService;
    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             TourServiceImpl tourService, IUserService userService) {
        this.reviewRepository = reviewRepository;
        this.tourService = tourService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Review createReview(Review review, User user) {
        if (reviewRepository.existsByUserIdAndTourId(user.getId(), review.getTour().getId())) {
            throw new DuplicateReviewException("User already has a review for this tour");
        }

        Review saved = reviewRepository.save(review);

        Tour tour = saved.getTour();
        if (tour == null || tour.getId() == null) {
            throw new IllegalArgumentException("Tour must be set for review");
        }
        tourService.updateTourStats(tour.getId());
        userService.updateGuideStats(saved.getTour().getCreator().getId());
        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean checkReviewExists(Long userId, Long tourId) {
        return reviewRepository.existsByUserIdAndTourId(userId, tourId);
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
    }

    @Override
    @Transactional
    public Review updateReview(Review review) {
        Review existingReview = getReviewById(review.getId());
        Review updatedReview = reviewRepository.save(review);

        if (!Objects.equals(existingReview.getRating(), review.getRating())) {
            Tour tour = review.getTour();
            if (tour != null && tour.getId() != null) {
                tourService.updateTourStats(tour.getId());
            }
        }
        return updatedReview;
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = getReviewById(reviewId);
        Long tourId = review.getTour().getId();
        reviewRepository.delete(review);
        tourService.updateTourStats(tourId);
    }

    @Override
    public List<Review> getReviewsByTour(Long tourId) {
        Tour tour = new Tour();
        tour.setId(tourId);
        return reviewRepository.findByTour(tour);
    }

    @Override
    public List<Review> getReviewsByUser(Long userId) {
        User user = new User();
        user.setId(userId);
        return reviewRepository.findByUser(user);
    }

    @Override
    public Double getAverageRatingByCreatorId(Long creatorId) {
        return reviewRepository.calculateAverageRatingByCreatorId(creatorId);
    }

    @Override
    public Integer getReviewCountByCreatorId(Long creatorId) {
        return reviewRepository.countReviewsByCreatorId(creatorId);
    }
}
