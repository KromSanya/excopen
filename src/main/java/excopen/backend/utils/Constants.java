package excopen.backend.utils;


import excopen.backend.servicesImpl.TagServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static int tagCount;

    @Autowired
    private TagServiceImpl tagService;

    // Используем аннотацию PostConstruct для инициализации
    @PostConstruct
    public void init() {
        tagCount = (int) tagService.getTagCount();
    }
}
