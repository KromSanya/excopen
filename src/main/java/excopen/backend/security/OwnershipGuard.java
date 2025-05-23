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
        User currentUser = null;
        Long resourceId = null;

        for (Object arg : args) {
            if (arg instanceof User) {
                currentUser = (User) arg;
            } else if (arg instanceof Long) {
                resourceId = (Long) arg;
            }
        }

        if (currentUser == null || resourceId == null) {
            throw new AccessDeniedException("Invalid request parameters");
        }

        Class<?> entityClass = requiresOwnership.entityClass();

        if (entityClass.equals(Tour.class)) {
            checkTourOwnership(resourceId, currentUser.getId());
        } else if (entityClass.equals(Review.class)) {
            checkReviewOwnership(resourceId, currentUser.getId());
        }

        return joinPoint.proceed();
    }


    private void checkTourOwnership(Long tourId, Long userId) {
        Tour tour = tourService.getTourById(tourId);
        if (!tour.getCreator().getId().equals(userId)) {
            throw new AccessDeniedException("You are not the owner of this tour");
        }
    }

    private void checkReviewOwnership(Long reviewId, Long userId) {
        Review review = reviewService.getReviewById(reviewId);
        if (!review.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You are not the owner of this review");
        }
    }

}
