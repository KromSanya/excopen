package excopen.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBookingRequest {

    @NotNull(message = "ID слота обязателен")
    private Long bookingSlotId;

    @NotNull(message = "Количество участников обязательно")
    @Min(value = 1, message = "Количество участников должно быть хотя бы 1")
    private Integer participants;
}
