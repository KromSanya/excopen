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
    @Mapping(target = "mainInfo", source = "dto.info")
    @Mapping(target = "orgDetails", source = "dto.orgDetails")
    @Mapping(target = "meetingPlace", source = "dto.meetingPlace")
    @Mapping(target = "whatToExpect", source = "dto.whatToExpect")
    Description toEntity(DescriptionDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tour", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "places", expression = "java(splitString(dto.getPlaces()))")
    @Mapping(target = "topics", expression = "java(splitString(dto.getTopics()))")
    @Mapping(target = "mainInfo", source = "dto.info")
    @Mapping(target = "orgDetails", source = "dto.orgDetails")
    @Mapping(target = "meetingPlace", source = "dto.meetingPlace")
    @Mapping(target = "whatToExpect", source = "dto.whatToExpect")
    void updateFromDTO(DescriptionDTO dto, @MappingTarget Description entity);

    @Mapping(target = "tourId", source = "tour.id")
    @Mapping(target = "info", source = "description.mainInfo")
    @Mapping(target = "whatToExpect", source = "description.whatToExpect")
    @Mapping(target = "places", source = "description.places")
    @Mapping(target = "topics", source = "description.topics")
    @Mapping(target = "orgDetails", source = "description.orgDetails")
    @Mapping(target = "meetingPlace", source = "description.meetingPlace")
    DescriptionResponseDTO toResponseDTO(Description description);

    default List<String> splitString(List<String> input) {
        return input;
    }

    default List<String> splitString(String input) {
        if (input == null || input.isBlank()) return Collections.emptyList();
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
    }
}
