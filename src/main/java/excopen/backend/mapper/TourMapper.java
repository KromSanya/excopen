package excopen.backend.mapper;

import excopen.backend.dto.TourCreateDTO;
import excopen.backend.dto.TourResponseDTO;
import excopen.backend.dto.TourUpdateDTO;
import excopen.backend.entities.Description;
import excopen.backend.entities.Tour;
import excopen.backend.iservices.IDescriptionService;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = DescriptionMapper.class)
public interface TourMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "vectorRepresentation", source = "vectorRepresentation")
    Tour toEntity(TourCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "vectorRepresentation", source = "vectorRepresentation")
    Tour toEntity(TourUpdateDTO dto);

    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(TourUpdateDTO dto, @MappingTarget Tour entity);

    @Mapping(target = "id", source = "tour.id")
    TourResponseDTO toResponseDTO(Tour tour, Description description);


    default List<TourResponseDTO> toResponseDTOList(List<Tour> tours, IDescriptionService descriptionService) {
        if (tours == null) {
            return Collections.emptyList();
        }
        return tours.stream()
                .map(tour -> {
                    Description description = descriptionService.getDescriptionByTourId(tour.getId());
                    return toResponseDTO(tour, description);
                })
                .collect(Collectors.toList());
    }
}