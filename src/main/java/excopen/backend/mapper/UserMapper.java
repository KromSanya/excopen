package excopen.backend.mapper;

import excopen.backend.dto.UserCreateDTO;
import excopen.backend.dto.UserResponseDTO;
import excopen.backend.dto.UserUpdateDTO;
import excopen.backend.entities.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "preferencesVector", ignore = true)
    @Mapping(target = "secondVector", ignore = true)
    User toEntity(UserCreateDTO dto);

    UserResponseDTO toResponseDTO(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "googleId", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "secondVector", ignore = true)
    void updateFromDTO(UserUpdateDTO dto, @MappingTarget User user);
}
