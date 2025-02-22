package excopen.backend.mapper;

import excopen.backend.dto.FavoriteCreateDTO;
import excopen.backend.dto.FavoriteResponseDTO;
import excopen.backend.entities.Favorite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {

    @Mapping(target = "id", ignore = true)
    Favorite toEntity(FavoriteCreateDTO dto);

    FavoriteResponseDTO toResponseDTO(Favorite favorite);
}
