package excopen.backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TourCreateDTO {

    @NotBlank(message = "Название тура не может быть пустым")
    @Size(max = 100, message = "Название тура не должно превышать 100 символов")
    private String title;

    @NotNull(message = "Location id обязателен")
    private Long locationId;

    @NotNull(message = "Цена тура обязательна")
    @Positive(message = "Цена должна быть положительной")
    private BigDecimal price;

    @NotBlank(message = "Длительность тура обязательна")
    private String duration;

    @NotNull(message = "Длина маршрута обязательна")
    @Positive(message = "Длина маршрута должна быть положительной")
    private BigDecimal routeLength;

    @NotNull(message = "Минимальный возраст обязателен")
    private Integer minAge;

    @NotNull(message = "Максимальная вместимость обязательна")
    private Integer maxCapacity;

    @NotNull(message = "Необходимо выбрать категории для экскурсии")
    private int[] vectorRepresentation;

    @Valid
    @NotNull(message = "Описание обязательно")
    private DescriptionDTO description;
}
