package excopen.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "booking_slots")
public class BookingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tour_id", nullable = false)
    private Long tourId;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "available_slots", nullable = false)
    private Integer availableSlots;

    @Column(name = "max_participants", nullable = false)
    private Integer maxParticipants;
}
