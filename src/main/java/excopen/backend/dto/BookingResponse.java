package excopen.backend.dto;

import excopen.backend.constants.BookingStatus;

import java.time.LocalDateTime;

public class BookingResponse {
    private Long id;

    private Long bookingSlotId;

    private Long userId;

    private Integer participants;

    private BookingStatus status;

    private LocalDateTime createdAt;
}
