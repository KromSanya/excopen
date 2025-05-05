package excopen.backend.mapper;

import excopen.backend.servicesImpl.TagVectorService;
import org.mapstruct.Context;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagVectorMapper {

    @Named("tagsToVector")
    public int[] tagsToVector(List<String> tags, @Context TagVectorService service) {
        return service.toVector(tags);
    }

    @Named("vectorToTags")
    public List<String> vectorToTags(int[] vector, @Context TagVectorService service) {
        return service.toNames(vector);
    }
}
