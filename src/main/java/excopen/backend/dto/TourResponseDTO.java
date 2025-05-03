package excopen.backend.dto;

import excopen.backend.constants.TourAccessibility;
import excopen.backend.constants.TourType;
import excopen.backend.constants.TransportType;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TourResponseDTO {
    private Long id;
    private String title;
    private DescriptionDTO description;
    private List<String> images;
    private List<String> tags;
    private LocationResponseDTO location;
    private Double routeLength;
    private Boolean byCity;
    private Integer price;
    private Integer groupCapacity;
    private TransportType formatBehavior;
    private TourType format;
    private TourAccessibility accessibility;
    private Double duration;
    private Long contributorId;
    private Double rating;
    private Integer ratingCount;
}

