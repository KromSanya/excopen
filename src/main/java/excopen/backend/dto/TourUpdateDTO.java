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

//    @NotNull(message = "Location id обязателен")
    private LocationDTO location;

//    @NotNull(message = "Цена тура обязательна")
//    @Positive(message = "Цена должна быть положительной")
    private Integer price;

    private Integer priceForPerson;

//    @NotNull(message = "Длительность тура обязательна")
//    @Positive(message = "Длительность должна быть положительной")
    private Double duration;

//    @NotNull(message = "Длина маршрута обязательна")
//    @Positive(message = "Длина маршрута должна быть положительной")
    private Double routeLength;

//    @NotNull(message = "Необходимо выбрать категории для экскурсии")
    private List<String> tags;

//    @NotNull(message = "Максимальная вместимость обязательна")
    private Integer groupCapacity;

//    @NotNull(message = "Укажите формат экскурсии")
    private String format;

//    @NotNull(message = "Укажите тип транспорта")
    private String formatBehavior;

    private boolean byCity;

    private TourAccessibility accessibility;

    private CoordinateDTO coordinates;

    @Valid
    @NotNull(message = "Описание обязательно")
    private DescriptionDTO description;
}
