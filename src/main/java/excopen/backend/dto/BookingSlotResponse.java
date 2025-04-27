package excopen.backend.dto;

import java.time.LocalDateTime;

public class BookingSlotResponse {
    private Long id;

    private Long tourId;

    private LocalDateTime dateTime;

    private Integer availableSlots;

    private Integer maxParticipants;
}
