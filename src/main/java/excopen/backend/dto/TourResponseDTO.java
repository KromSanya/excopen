package excopen.backend.dto;

import excopen.backend.constants.TourAccessibility;
import excopen.backend.constants.TourType;
import excopen.backend.constants.TransportType;
import excopen.backend.entities.Coordinate;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private Integer priceForPerson;
    private Integer groupCapacity;

    private String formatBehavior;
    private String format;
    private TourAccessibility accessibility;

    private Double duration;
    private Long contributorId;

    private Double rating;
    private Integer ratingCount;

    private CoordinateDTO coordinates;

    private LocalDate date;
    private LocalTime time;

    private ContactDTO contacts;
}