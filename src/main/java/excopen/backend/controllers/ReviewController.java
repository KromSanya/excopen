package excopen.backend.controllers;

import excopen.backend.entities.Review;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import excopen.backend.iservices.IReviewService;
import excopen.backend.iservices.ITourService;
import excopen.backend.iservices.IUserService;
import excopen.backend.security.RequiresOwnership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final IReviewService reviewService;
    private final ITourService tourService;
    private final IUserService userService;

    @Autowired
    public ReviewController(IReviewService reviewService, ITourService tourService, IUserService userService) {
        this.reviewService = reviewService;
        this.tourService = tourService;
        this.userService = userService;
    }

    @PostMapping
    public Review createReview(@RequestBody Review review, @AuthenticationPrincipal OAuth2User principal) {
        String googleId = principal.getAttribute("sub");
        User user = userService.findByGoogleId(googleId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Tour tour = tourService.getTourById(review.getTourId());

        review.setUserId(user.getId());
        review.setTourId(tour.getId());
        return reviewService.createReview(review);
    }

    @GetMapping("/{reviewId}")
    public Review getReviewById(@PathVariable Long reviewId) {
        return reviewService.getReviewById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
    }

    @RequiresOwnership(entityClass = Review.class)
    @PutMapping("/{reviewId}")
    public Review updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        Review existingReview = reviewService.getReviewById(reviewId).orElseThrow(() -> new IllegalArgumentException("Review nor found"));
        return reviewService.updateReview(review);
    }

    @RequiresOwnership(entityClass = Review.class)
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal OAuth2User principal) {
        reviewService.deleteReview(reviewId);
    }

    @GetMapping("/tour/{tourId}")
    public List<Review> getReviewsByTour(@PathVariable Long tourId) {
        return reviewService.getReviewsByTour(tourId);
    }

    @GetMapping("/user/{userId}")
    public List<Review> getReviewsByUser(@PathVariable Long userId) {
        return reviewService.getReviewsByUser(userId);
    }

    @GetMapping("/tour/{tourId}/average-rating")
    public double getAverageRatingForTour(@PathVariable Long tourId) {
        return reviewService.getAverageRatingForTour(tourId);
    }
}
