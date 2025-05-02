package excopen.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tour_schedules")
@Data
public class TourSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tour_id", nullable = false)
    private Long tourId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "repeat_interval", nullable = false)
    private int repeatInterval = 1; // в днях

    @Column(name = "max_participants", nullable = false)
    private int maxParticipants;
}

