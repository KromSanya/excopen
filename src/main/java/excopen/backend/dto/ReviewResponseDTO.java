package excopen.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewResponseDTO {
    private Long id;
    private Long tourId;
    private Long userId;
    private Double rating;
    private Boolean withChildren;
    private Integer personCount;
    private String positiveText;
    private String negativeText;
//    private List<String> imageUrls;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
