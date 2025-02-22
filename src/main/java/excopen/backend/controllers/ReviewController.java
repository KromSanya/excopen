package excopen.backend.controllers;

import excopen.backend.dto.ReviewCreateDTO;
import excopen.backend.dto.ReviewResponseDTO;
import excopen.backend.dto.ReviewUpdateDTO;
import excopen.backend.entities.Review;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import excopen.backend.iservices.IReviewService;
import excopen.backend.iservices.ITourService;
import excopen.backend.iservices.IUserService;
import excopen.backend.mapper.ReviewMapper;
import excopen.backend.security.RequiresOwnership;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final IReviewService reviewService;
    private final ITourService tourService;
    private final IUserService userService;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewController(IReviewService reviewService, ITourService tourService, IUserService userService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.tourService = tourService;
        this.userService = userService;
        this.reviewMapper = reviewMapper;
    }

    @PostMapping
    public ReviewResponseDTO createReview(@Valid @RequestBody ReviewCreateDTO reviewDTO,
                                          @AuthenticationPrincipal OAuth2User principal) {
        String googleId = principal.getAttribute("sub");
        User user = userService.getUserByGoogleId(googleId);

        Tour tour = tourService.getTourById(reviewDTO.getTourId());

        Review review = reviewMapper.toEntity(reviewDTO);
        review.setUserId(user.getId());
        review.setTourId(tour.getId());

        return reviewMapper.toResponseDTO(reviewService.createReview(review));
    }

    @GetMapping("/{reviewId}")
    public ReviewResponseDTO getReviewById(@PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        return reviewMapper.toResponseDTO(review);
    }

    @RequiresOwnership(entityClass = Review.class)
    @PutMapping("/{reviewId}")
    public ReviewResponseDTO updateReview(@Valid @PathVariable Long reviewId,
                                          @RequestBody ReviewUpdateDTO reviewDTO) {
        Review existingReview = reviewService.getReviewById(reviewId);

        reviewMapper.updateReviewFromDTO(reviewDTO, existingReview);

        return reviewMapper.toResponseDTO(reviewService.updateReview(existingReview));
    }

    @RequiresOwnership(entityClass = Review.class)
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }

    @GetMapping("/tour/{tourId}")
    public List<ReviewResponseDTO> getReviewsByTour(@PathVariable Long tourId) {
        return reviewMapper.toResponseDTOList(reviewService.getReviewsByTour(tourId));
    }

    @GetMapping("/user/{userId}")
    public List<ReviewResponseDTO> getReviewsByUser(@PathVariable Long userId) {
        return reviewMapper.toResponseDTOList(reviewService.getReviewsByUser(userId));
    }

    @GetMapping("/tour/{tourId}/average-rating")
    public double getAverageRatingForTour(@PathVariable Long tourId) {
        return reviewService.getAverageRatingForTour(tourId);
    }
}
