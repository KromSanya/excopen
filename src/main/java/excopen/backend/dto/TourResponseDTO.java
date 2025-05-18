package excopen.backend.dto;

import excopen.backend.constants.TourAccessibility;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class TourResponseDTO {

    private Long id;
    private String title;

    private DescriptionResponseDTO description;
    private List<String> images;
    private List<String> tags;

    private LocationDTO location;

    private Double routeLength;
    private Boolean byCity;
    private Integer price;
    private Integer priceForPerson;
    private Integer groupCapacity;
    private Integer freeSeats;

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