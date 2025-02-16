package excopen.backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;

@Data
public class TourUpdateDTO {

    @NotNull(message = "ID тура обязателен")
    private Long id;

    @NotBlank(message = "Название тура не может быть пустым")
    @Size(max = 100, message = "Название тура не должно превышать 100 символов")
    private String title;

    @NotNull(message = "Location id обязателен")
    private Long locationId;

    @NotNull(message = "Цена тура обязательна")
    @DecimalMin(value = "0.0", inclusive = false, message = "Цена должна быть положительной")
    private BigDecimal price;

    @NotBlank(message = "Длительность тура обязательна")
    private String duration;

    @NotNull(message = "Длина маршрута обязательна")
    @Positive(message = "Длина маршрута должна быть положительной")
    private BigDecimal routeLength;

    @NotNull(message = "Необходимо выбрать категории для экскурсии")
    @JdbcTypeCode(SqlTypes.VECTOR)
    private int[] vectorRepresentation;

    @NotNull(message = "Минимальный возраст обязателен")
    private Integer minAge;

    @NotNull(message = "Максимальная вместимость обязательна")
    private Integer maxCapacity;

    @Valid
    @NotNull(message = "Описание обязательно")
    private DescriptionDTO description;
}
