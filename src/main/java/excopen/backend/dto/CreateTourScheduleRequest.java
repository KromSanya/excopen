package excopen.backend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateTourScheduleRequest {

    @NotNull(message = "tourId обязателен")
    private Long tourId;

    @NotNull(message = "Дата начала обязательна")
    @FutureOrPresent(message = "Дата начала не может быть в прошлом")
    private LocalDate startDate;

    @Future(message = "Дата окончания должна быть в будущем")
    private LocalDate endDate;

    @NotNull(message = "Время начала обязательно")
    private LocalTime time;

    @Min(value = 1, message = "Интервал повтора должен быть хотя бы 1 день")
    private int repeatInterval = 1;
}
