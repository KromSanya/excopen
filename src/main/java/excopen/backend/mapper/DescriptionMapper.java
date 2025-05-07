package excopen.backend.mapper;

import excopen.backend.dto.DescriptionDTO;
import excopen.backend.dto.DescriptionResponseDTO;
import excopen.backend.entities.Description;
import org.mapstruct.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DescriptionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "places", expression = "java(splitString(dto.getPlaces()))")
    @Mapping(target = "topics", expression = "java(splitString(dto.getTopics()))")
    Description toEntity(DescriptionDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tour", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "places", expression = "java(splitString(dto.getPlaces()))")
    @Mapping(target = "topics", expression = "java(splitString(dto.getTopics()))")
    void updateFromDTO(DescriptionDTO dto, @MappingTarget Description entity);

    @Mapping(target = "tourId", source = "tour.id")
    DescriptionResponseDTO toResponseDTO(Description description);

    default List<String> splitString(List<String> input) {
        // уже список — просто верни
        return input;
    }

    default List<String> splitString(String input) {
        if (input == null || input.isBlank()) return Collections.emptyList();
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
    }
}
