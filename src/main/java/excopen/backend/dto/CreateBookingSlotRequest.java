package excopen.backend.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateBookingSlotRequest {

    @NotNull(message = "tourId обязателен")
    private Long tourId;

    @NotNull(message = "Дата и время начала обязательны")
    @Future(message = "Дата и время начала должны быть в будущем")
    private LocalDateTime startDateTime;

    @Min(value = 1, message = "Количество участников должно быть больше 0")
    private int maxParticipants;
}
