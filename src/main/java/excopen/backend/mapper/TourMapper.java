package excopen.backend.mapper;

import excopen.backend.dto.TourCreateDTO;
import excopen.backend.dto.TourResponseDTO;
import excopen.backend.dto.TourUpdateDTO;
import excopen.backend.entities.Location;
import excopen.backend.entities.Tour;
import excopen.backend.entities.TourImage;
import excopen.backend.servicesImpl.TagVectorService;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;
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
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "vectorRepresentation", source = "dto", qualifiedByName = "tagsToVector")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "accessibility", source = "dto.accessibility")
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    @Mapping(target = "images", ignore = true)
    Tour toEntity(TourCreateDTO dto, Location location, @Context TagVectorService svc);

    // ----------- UPDATE -----------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "vectorRepresentation", source = "dto", qualifiedByName = "tagsToVector")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "accessibility", source = "dto.accessibility")
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "reviewCount", ignore = true)
    Tour toEntity(TourUpdateDTO dto, Location location, @Context TagVectorService svc);

    // ----------- VECTOR CONVERSION -----------
    @Named("tagsToVector")
    default int[] mapTagsToVector(TourCreateDTO dto, @Context TagVectorService svc) {
        return svc.toVector(dto.getTags());
    }

    @Named("tagsToVector")
    default int[] mapTagsToVector(TourUpdateDTO dto, @Context TagVectorService svc) {
        return svc.toVector(dto.getTags());
    }

    @Named("toNames")
    default List<String> mapVectorToTags(int[] vector, @Context TagVectorService svc) {
        return svc.toNames(vector);
    }

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
    TourResponseDTO toResponseDTO(Tour tour, @Context TagVectorService svc);

    default List<TourResponseDTO> toResponseDTOList(List<Tour> tours, @Context TagVectorService svc) {
        if (tours == null) return Collections.emptyList();
        return tours.stream()
                .map(t -> toResponseDTO(t, svc))
                .collect(Collectors.toList());
    }

    @Named("mapTourImages")
    default List<String> mapTourImages(List<TourImage> images) {
        if (images == null) return List.of();
        return images.stream()
                .map(TourImage::getImageUrl)
                .toList();
    }
}
