package excopen.backend.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TourScheduleResponse {

    private Long id;

    private Long tourId;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime time;

    private Integer repeatInterval;
}