package excopen.backend.dto;

import excopen.backend.entities.Description;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TourResponseDTO {
    private Long id;
    private String title;
    private Long locationId;
    private BigDecimal price;
    private String duration;
    private BigDecimal routeLength;
    private Integer minAge;
    private Integer maxCapacity;
    private Long creatorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Description description;
}