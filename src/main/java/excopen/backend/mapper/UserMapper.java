package excopen.backend.mapper;

import excopen.backend.dto.*;
import excopen.backend.entities.TourImage;
import excopen.backend.entities.User;
import excopen.backend.servicesImpl.TagVectorService;
import jakarta.servlet.http.HttpServletRequest;
import org.mapstruct.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Objects;

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
    @Mapping(source = "avatarUrl", target = "avatar", qualifiedByName = "mapAvatar")
    UserResponseDTO toUserResponseDTO(User user, @Context HttpServletRequest request);

    @Mapping(target = "contacts", source = ".")
    @Mapping(source = "avatarUrl", target = "avatar", qualifiedByName = "mapAvatar")
    @Mapping(source = "description", target = "info")
    GuideResponseDTO toGuideResponseDTO(User user, @Context HttpServletRequest request);

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

    @Named("mapAvatar")
    default String mapAvatar(String image, @Context HttpServletRequest request) {
        final String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();

        if (image.matches("^(?i)https?://.*")) {
            return image;
        }
        return image.startsWith("/")
                ? baseUrl + image
                : baseUrl + "/" + image;


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
