package excopen.backend.dto;

import excopen.backend.constants.TourType;
import excopen.backend.constants.TransportType;
import excopen.backend.entities.Description;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TourResponseDTO {
    private Long id;
    private String title;
    private Long locationId;
    private Integer price;
    private BigDecimal duration;
    private BigDecimal routeLength;
    private Integer minAge;
    private Integer maxCapacity;
    private BigDecimal rating;
    private Long creatorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private TourType tourType;
    private TransportType transportType;
    private Description description;
}
