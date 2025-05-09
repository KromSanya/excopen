package excopen.backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CoordinateDTO {
    @NotNull
    @Valid
    private Point point;

    @NotNull
    private Double zoom;

    @Data
    public static class Point {
        @NotNull
        private Double latitude;

        @NotNull
        private Double longitude;
    }
}
