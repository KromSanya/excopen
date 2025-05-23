package excopen.backend.mapper;

import excopen.backend.dto.ReviewCreateDTO;
import excopen.backend.dto.ReviewResponseDTO;
import excopen.backend.dto.ReviewUpdateDTO;
import excopen.backend.entities.Review;
import excopen.backend.entities.TourImage;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Review toEntity(ReviewCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateReviewFromDTO(ReviewUpdateDTO dto, @MappingTarget Review review);

    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "tourId", source = "tour.id")

    ReviewResponseDTO toResponseDTO(Review review);

    default List<ReviewResponseDTO> toResponseDTOList(List<Review> reviews) {
        if (reviews == null) {
            return Collections.emptyList();
        }
        return reviews.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

//    default List<String> mapReviewImages(List<ReviewImage> images) {
//        if (images == null) return List.of();
//        return images.stream()
//                .map(ReviewImage::getImageUrl)
//                .toList();
//    }
}