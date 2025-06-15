package excopen.backend.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.server.ResponseStatusException;
import java.security.Principal;

public class SecurityUtils {

    public static void checkAuthentication(OAuth2User principal) {
        if (principal == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Пользователь не аутентифицирован"
            );
        }
    }
}
