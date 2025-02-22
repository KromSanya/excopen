package excopen.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FavoriteResponseDTO {
    private Long id;
    private Long userId;
    private Long tourId;

}
