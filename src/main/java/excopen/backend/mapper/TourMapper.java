package excopen.backend.mapper;

import excopen.backend.dto.*;
import excopen.backend.entities.*;
import excopen.backend.servicesImpl.TagVectorService;
import jakarta.servlet.http.HttpServletRequest;
import org.mapstruct.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {DescriptionMapper.class, LocationMapper.class})
public interface TourMapper {

    // ----------- CREATE -----------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "reviewCount", ignore = true)

    @Mapping(target = "title", source = "dto.title")
    @Mapping(target = "price", source = "dto.price")
    @Mapping(target = "duration", source = "dto.duration")
    @Mapping(target = "routeLength", source = "dto.routeLength")
    @Mapping(target = "maxCapacity", source = "dto.groupCapacity")
    @Mapping(target = "freeSeats", source = "dto.groupCapacity")

    @Mapping(target = "tourType", source = "dto.format")
    @Mapping(target = "transportType", source = "dto.formatBehavior")
    @Mapping(target = "accessibility", source = "dto.accessibility")

    @Mapping(target = "byCity", source = "dto.byCity")
    @Mapping(target = "date", source = "dto.date")
    @Mapping(target = "time", source = "dto.time")

    @Mapping(target = "coordinate", source = "dto.coordinates")
    @Mapping(target = "contacts", source = "dto.contacts")

    @Mapping(source = "dto.tags", target = "vectorRepresentation", qualifiedByName = "tagsToVector")
    @Mapping(target = "location", source = "location")

    @Mapping(target = "description", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    Tour toEntity(TourCreateDTO dto, Location location, @Context TagVectorService svc);

    // ----------- UPDATE -----------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(source = "dto.tags", target = "vectorRepresentation", qualifiedByName = "tagsToVector")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "accessibility", source = "dto.accessibility")
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "reviewCount", ignore = true)
    Tour toEntity(TourUpdateDTO dto, Location location, @Context TagVectorService svc);



    // ----------- RESPONSE DTO -----------
    @Mapping(target = "description", source = "description")
    @Mapping(source = "images", target = "images", qualifiedByName = "mapTourImages")
    @Mapping(target = "tags", source = "vectorRepresentation", qualifiedByName = "toNames")
    @Mapping(target = "accessibility", source = "accessibility")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "formatBehavior", source = "transportType")
    @Mapping(target = "format", source = "tourType")
    @Mapping(target = "groupCapacity", source = "maxCapacity")
    @Mapping(target = "contributorId", source = "creator.id")
    @Mapping(target = "ratingCount", source = "reviewCount")
    @Mapping(target = "coordinates", source = "coordinate")
    TourResponseDTO toResponseDTO(Tour tour,
                                  @Context TagVectorService svc,
                                  @Context HttpServletRequest request);

    default List<TourResponseDTO> toResponseDTOList(List<Tour> tours, @Context TagVectorService svc, @Context HttpServletRequest request) {
        if (tours == null) return Collections.emptyList();
        return tours.stream()
                .map(t -> toResponseDTO(t, svc, request))
                .collect(Collectors.toList());
    }

    @Named("mapTourImages")
    default List<String> mapTourImages(List<TourImage> images, @Context HttpServletRequest request) {
        if (images == null) return List.of();

        final String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();

        return images.stream()
                .map(TourImage::getImageUrl)
                .filter(Objects::nonNull)
                .map(url -> {
                    if (url.matches("^(?i)https?://.*")) {
                        return url;
                    }
                    return url.startsWith("/")
                            ? baseUrl + url
                            : baseUrl + "/" + url;
                })
                .toList();
    }

    default CoordinateDTO mapCoordinateToDto(Coordinate coordinate) {
        if (coordinate == null) return null;

        CoordinateDTO dto = new CoordinateDTO();
        CoordinateDTO.Point point = new CoordinateDTO.Point();
        point.setLatitude(coordinate.getLatitude());
        point.setLongitude(coordinate.getLongitude());
        dto.setPoint(point);
        dto.setZoom(coordinate.getZoom());
        return dto;
    }

    default Coordinate toEntity(CoordinateDTO dto) {
        Coordinate coord = new Coordinate();
        coord.setLongitude(dto.getPoint().getLongitude());
        coord.setLatitude(dto.getPoint().getLatitude());
        coord.setZoom(dto.getZoom());
        return coord;
    }

    default Contact toEntity(ContactDTO dto) {
        Contact c = new Contact();
        c.setPhone(dto.getPhone());
        c.setVk(dto.getVk());
        c.setTelegram(dto.getTelegram());
        return c;
    }

    // ----------- VECTOR CONVERSION -----------
    @Named("tagsToVector")
    default int[] mapTagsToVector(List<String> tags, @Context TagVectorService svc) {
        return svc.toVector(tags);
    }

    @Named("toNames")
    default List<String> mapVectorToTags(int[] vector, @Context TagVectorService svc) {
        return svc.toNames(vector);
    }
}
