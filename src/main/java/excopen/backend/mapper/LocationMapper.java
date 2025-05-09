package excopen.backend.mapper;

import excopen.backend.dto.LocationDTO;
import excopen.backend.entities.Location;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mapping(target = "imageUrl", source = "image")
    Location toEntity(LocationDTO dto);

    @Mapping(target = "image", source = "imageUrl")
    LocationDTO toResponseDTO(Location entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(LocationDTO dto, @MappingTarget Location entity);
}
