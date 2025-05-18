package excopen.backend.dto;

import excopen.backend.constants.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponse {
    private Long id;

//    private Long bookingSlotId;

    private Long tourId;

    private Long userId;

    private Integer groupCapacity;

//    private BookingStatus status;

//    private LocalDateTime createdAt;
}
