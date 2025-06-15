package excopen.backend.dto;

import excopen.backend.constants.TourAccessibility;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class TourCreateDTO {

//    @NotBlank(message = "Название тура не может быть пустым")
//    @Size(max = 100, message = "Название тура не должно превышать 100 символов")
    private String title;

//    @NotNull(message = "Location id обязателен")
    private LocationDTO location;

    private Integer price;

    private Integer priceForPerson;

//    @NotNull(message = "Длительность тура обязательна")
 //   @Positive(message = "Длительность должна быть положительной")
    private Double duration;

 //   @NotNull(message = "Длина маршрута обязательна")
 //   @Positive(message = "Длина маршрута должна быть положительной")
    private Double routeLength;

//    @NotNull(message = "Максимальная вместимость обязательна")
    private Integer groupCapacity;

//    @NotNull(message = "Необходимо выбрать категории для экскурсии")
    private List<String> tags;
 //   @NotNull(message = "Укажите формат экскурсии")
    private String format;

//    @NotNull(message = "Укажите тип транспорта")
    private String formatBehavior;

    private boolean byCity;

    private TourAccessibility accessibility;

//    @Valid
 //   @NotNull(message = "Координаты обязательны")
    private CoordinateDTO coordinates;

 //   @Valid
 //   @NotNull(message = "Описание обязательно")
    private DescriptionDTO description;
}
