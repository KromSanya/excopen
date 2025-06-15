package excopen.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class CreateBookingSlotRequestBadVersion {
    @NotNull(message = "Tour ID is required")
    private Long tourId;

    @NotNull(message = "Date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotBlank(message = "Time is required")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Invalid time format")
    private String time;

    @Min(value = 1, message = "Group capacity must be at least 1")
    private int groupCapacity;

    // Метод для преобразования в LocalDateTime
    public LocalDateTime getDateTime() {
        LocalTime localTime = LocalTime.parse(time);
        return LocalDateTime.of(date, localTime);
    }
}
