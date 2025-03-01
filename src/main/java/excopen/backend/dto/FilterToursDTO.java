package excopen.backend.dto;

import excopen.backend.constants.TourType;
import excopen.backend.constants.TransportType;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterToursDTO {
    private String title;
    private Long locationId;
    @Min(value = 0, message = "Цена не может быть отрицательной")
    private Integer priceFrom;
    @Min(value = 0, message = "Цена не может быть отрицательной")
    private Integer priceTo;
    @Min(value = 0, message = "Длительность не может быть отрицательной")
    private BigDecimal durationFrom;
    @Min(value = 0, message = "Длительность не может быть отрицательной")
    private BigDecimal durationTo;
    @Min(value = 0, message = "Протяжённость маршрута не может быть отрицательной")
    private BigDecimal routeLengthFrom;
    @Min(value = 0, message = "Протяжённость маршрута не может быть отрицательной")
    private BigDecimal routeLengthTo;
    @Min(value = 0, message = "Рейтинг не может быть отрицательным")
    private BigDecimal ratingFrom;
    @Min(value = 0, message = "Рейтинг не может быть отрицательным")
    private BigDecimal ratingTo;
    private TourType tourType;
    private TransportType transportType;
    @Min(value = 0, message = "Минимальный возраст не может быть отрицательным")
    private Integer minAge;
    @Min(value = 0, message = "Вместимость не может быть отрицательной")
    private Integer capacity;
}