package excopen.backend.dto;

import excopen.backend.constants.TourAccessibility;
import excopen.backend.constants.TourType;
import excopen.backend.constants.TransportType;
import excopen.backend.entities.Coordinate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class TourUpdateDTO {

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
    private Double duration;

    @NotNull(message = "Длина маршрута обязательна")
    @Positive(message = "Длина маршрута должна быть положительной")
    private Double routeLength;

    @NotNull(message = "Необходимо выбрать категории для экскурсии")
    private List<String> tags;


    @NotNull(message = "Максимальная вместимость обязательна")
    private Integer maxCapacity;

    @NotNull(message = "Укажите формат экскурсии")
    private TourType tourType;

    @NotNull(message = "Укажите тип транспорта")
    private TransportType transportType;

    private boolean byCity;

    private TourAccessibility accessibility;

    private Coordinate coordinates;

    private LocalDate date;

    private LocalTime time;

    @Valid
    @NotNull(message = "Описание обязательно")
    private DescriptionDTO description;
}
