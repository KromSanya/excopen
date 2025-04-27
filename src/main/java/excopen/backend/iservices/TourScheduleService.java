package excopen.backend.iservices;

import excopen.backend.entities.TourSchedule;

import java.util.List;

public interface TourScheduleService {
    TourSchedule createSchedule(TourSchedule schedule, Long userId);
    TourSchedule updateSchedule(TourSchedule schedule, Long userId);
    void deleteSchedule(Long scheduleId, Long userId);
    List<TourSchedule> getSchedulesForTour(Long tourId);
}
