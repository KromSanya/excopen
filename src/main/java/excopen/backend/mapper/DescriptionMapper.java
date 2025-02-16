package excopen.backend.mapper;

import excopen.backend.dto.DescriptionDTO;
import excopen.backend.dto.DescriptionResponseDTO;
import excopen.backend.entities.Description;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DescriptionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tourId", ignore = true)
    Description toEntity(DescriptionDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tourId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(DescriptionDTO dto, @MappingTarget Description entity);

    DescriptionResponseDTO toResponseDTO(Description description);
}
