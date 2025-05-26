package excopen.backend.config;

import excopen.backend.security.CurrentUserArgumentResolver;
import excopen.backend.iservices.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final IUserService userService;

    @Autowired
    public WebMvcConfig(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentUserArgumentResolver(userService));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/tour_images/**")
                .addResourceLocations("file:uploads/tour_images/");
        registry.addResourceHandler("/static/review_images/**")
                .addResourceLocations("file:uploads/review_images/");
        registry.addResourceHandler("/static/locations/**")
                .addResourceLocations("file:uploads/locations/");
        registry.addResourceHandler("/static/user_avatars/**")
                .addResourceLocations("file:uploads/user_avatars/");
    }
}
