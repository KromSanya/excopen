package excopen.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CoordinateDTO {
    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private Double zoom;
}
