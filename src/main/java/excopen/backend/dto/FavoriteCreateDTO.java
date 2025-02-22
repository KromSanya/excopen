package excopen.backend.dto;

import lombok.Data;

@Data
public class FavoriteCreateDTO {
    private Long userId;
    private Long tourId;
}