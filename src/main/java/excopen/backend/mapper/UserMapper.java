package excopen.backend.mapper;

import excopen.backend.dto.*;
import excopen.backend.entities.User;
import excopen.backend.servicesImpl.TagVectorService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TagVectorService.class })
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "preferencesVector", ignore = true)
    @Mapping(target = "secondVector", ignore = true)
    User toEntity(UserCreateDTO dto);

    @Mapping(source = "preferencesVector", target = "tags")
    @Mapping(target = "contacts", source = ".")
    @Mapping(source = "avatarUrl", target = "avatar")
    UserResponseDTO toUserResponseDTO(User user);

    @Mapping(target = "contacts", source = ".")
    @Mapping(source = "avatarUrl", target = "avatar")
    GuideResponseDTO toGuideResponseDTO(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "googleId", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "secondVector", ignore = true)
    @Mapping(target = "preferencesVector", source = "tags")
    @Mapping(target = "contacts.vk", source = "contacts.vk")
    @Mapping(target = "contacts.telegram", source = "contacts.telegram")
    @Mapping(target = "contacts.phone", source = "contacts.phone")
//    @Mapping(target = "avatarUrl", source = "avatar")
    void updateFromDTO(UserUpdateDTO dto, @MappingTarget User user);

    @Named("toVector")
    default int[] mapTagsToVector(List<String> tags, @Context TagVectorService svc) {
        return svc.toVector(tags);
    }

    @Named("toNames")
    default List<String> mapVectorToTags(int[] vector, @Context TagVectorService svc) {
        return svc.toNames(vector);
    }

    default ContactDTO mapContacts(User user) {
        if (user.getContacts() == null) {
            return null;
        }
        ContactDTO contacts = new ContactDTO();
        contacts.setVk(user.getContacts().getVk());
        contacts.setTelegram(user.getContacts().getTelegram());
        contacts.setPhone(user.getContacts().getPhone());
        return contacts;
    }

}
