package excopen.backend.security;

import excopen.backend.servicesImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final UserServiceImpl customOAuth2UserService;

    public SecurityConfig(UserServiceImpl customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/**")
                )
//                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET,
                                "/test-tour-upload.html",
                                "/test-review-upload.html",
                                "/tour-details.html",
                                "/**",
                                "/static/**",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/api/favorites",
                                "/api/locations",
                                "/api/locations/**",
                                "/api/reviews/tour/**",
                                "/api/reviews/user/**",
                                "/api/tours/**",
                                "/api/users/**",
                                "/api/tags",
                                "/api/tours/search"
                        ).permitAll()

                        .requestMatchers(HttpMethod.POST,
                                "/api/users/apply-guide",
                                "/api/users/confirm-guide",
                                "/api/reviews",
                                "/api/favorites/**",
                                "/api/tours",
                                "/api/tours/**",
                                "/logout"
                        ).permitAll()
                        .requestMatchers(HttpMethod.PUT,
                                "/api/reviews/**",
                                "/api/tours/**",
                                "/api/users/me",
                                "/api/users/me/preferences-vector"
                        ).permitAll()

                        .requestMatchers(HttpMethod.DELETE,
                                "/api/favorites/**",
                                "/api/tours/**"
                        ).authenticated()

                        .anyRequest().denyAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                        .successHandler((request, response, authentication) -> {
                            response.sendRedirect("http://localhost:4173/main");
                        })
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/").permitAll()
                )
                .exceptionHandling(eh -> eh
                        .authenticationEntryPoint((req, res, ex) ->
                                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Пользователь не авторизован."))
                        .accessDeniedHandler((req, res, ex) ->
                                res.sendError(HttpServletResponse.SC_FORBIDDEN, "Доступ запрещён."))
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080", "http://localhost:5173", "http://localhost:4173",
                "https://excopenprodfront.vercel.app", "https://excopen.ru", "https://www.excopen.ru"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
