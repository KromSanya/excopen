package excopen.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReviewResponseDTO {
    private Long id;
    private Long tourId;
    private Long userId;
    private Integer rating;
    private String reviewText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
