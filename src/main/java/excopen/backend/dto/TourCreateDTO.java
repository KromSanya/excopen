package excopen.backend.dto;

import excopen.backend.constants.TourType;
import excopen.backend.constants.TransportType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
    private Integer price;

    @NotNull(message = "Длительность тура обязательна")
    @Positive(message = "Длительность должна быть положительной")
    @Digits(integer = 2, fraction = 1)
    private BigDecimal duration;

    @NotNull(message = "Длина маршрута обязательна")
    @Positive(message = "Длина маршрута должна быть положительной")
    @Digits(integer = 2, fraction = 1)
    private BigDecimal routeLength;

    @NotNull(message = "Минимальный возраст обязателен")
    private Integer minAge;

    @NotNull(message = "Максимальная вместимость обязательна")
    private Integer maxCapacity;

    @NotNull(message = "Необходимо выбрать категории для экскурсии")
    private int[] vectorRepresentation;

    @NotNull(message = "Укажите формат экскурсии")
    private TourType tourType;

    @NotNull(message = "Укажите тип транспорта")
    private TransportType transportType;

    @Valid
    @NotNull(message = "Описание обязательно")
    private DescriptionDTO description;
}
