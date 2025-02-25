package excopen.backend.security;

import excopen.backend.entities.Review;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import excopen.backend.iservices.IReviewService;
import excopen.backend.iservices.ITourService;
import excopen.backend.iservices.IUserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OwnershipGuard {

    private final IReviewService reviewService;
    private final ITourService tourService;
    private final IUserService userService;

    public OwnershipGuard(IReviewService reviewService, ITourService tourService, IUserService userService) {
        this.reviewService = reviewService;
        this.tourService = tourService;
        this.userService = userService;
    }

    @Around("@annotation(requiresOwnership)")
    public Object checkOwnership(ProceedingJoinPoint joinPoint, RequiresOwnership requiresOwnership) throws Throwable {
        Object[] args = joinPoint.getArgs();
        OAuth2User principal = null;
        Long resourceId = null;

        for (Object arg : args) {
            if (arg instanceof OAuth2User) {
                principal = (OAuth2User) arg;
            } else if (arg instanceof Long) {
                resourceId = (Long) arg;
            }
        }

        if (principal == null || resourceId == null) {
            throw new AccessDeniedException("Invalid request parameters");
        }

        // Получаем пользователя
        String googleId = principal.getAttribute("sub");
        User currentUser = userService.findByGoogleId(googleId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Long userId = currentUser.getId();
        Class<?> entityClass = requiresOwnership.entityClass();

        if (entityClass.equals(Tour.class)) {
            checkTourOwnership(resourceId, userId);
        } else if (entityClass.equals(Review.class)) {
            checkReviewOwnership(resourceId, userId);
        } else {
            throw new UnsupportedOperationException("Unsupported entity class: " + entityClass.getName());
        }

        return joinPoint.proceed();
    }


    private void checkTourOwnership(Long tourId, Long userId) {
        Tour tour = tourService.getTourById(tourId);
        if (!tour.getCreatorId().equals(userId)) {
            throw new AccessDeniedException("You are not the owner of this tour");
        }
    }

    private void checkReviewOwnership(Long reviewId, Long userId) {
        Review review = reviewService.getReviewById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        if (!review.getUserId().equals(userId)) {
            throw new AccessDeniedException("You are not the owner of this review");
        }
    }
}
