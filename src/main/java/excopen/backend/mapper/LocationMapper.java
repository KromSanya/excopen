package excopen.backend.mapper;

import excopen.backend.dto.LocationDTO;
import excopen.backend.entities.Location;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    Location toEntity(LocationDTO dto);

    LocationDTO toResponseDTO(Location entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(LocationDTO dto, @MappingTarget Location entity);
}
