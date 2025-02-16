package excopen.backend.security;

import excopen.backend.entities.User;
import excopen.backend.iservices.IUserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAuthorizationGuard {

    private final IUserService userService;

    public UserAuthorizationGuard(IUserService userService) {
        this.userService = userService;
    }

    @Around("@annotation(excopen.backend.security.RequiresUserAuthorization)")
    public Object checkUserAuthorization(ProceedingJoinPoint joinPoint) throws Throwable {
        Long userId = getUserId(joinPoint);
        OAuth2User principal = getPrincipal(joinPoint);

        if (principal == null) {
            throw new AccessDeniedException("Access denied: No authenticated user found");
        }

        User currentUser = userService.findByGoogleId(principal.getAttribute("sub"))
                .orElseThrow(() -> new IllegalArgumentException("Current user not found"));

        if (!currentUser.getId().equals(userId)) {
            throw new AccessDeniedException("Access denied: You are not authorized to access this resource");
        }

        return joinPoint.proceed();
    }

    private static Long getUserId(ProceedingJoinPoint joinPoint) {
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof Long) {
                return (Long) arg;
            }
        }
        throw new IllegalArgumentException("User ID not found in method arguments");
    }

    private static OAuth2User getPrincipal(ProceedingJoinPoint joinPoint) {
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof OAuth2User) {
                return (OAuth2User) arg;
            }
        }
        return null;
    }
}
