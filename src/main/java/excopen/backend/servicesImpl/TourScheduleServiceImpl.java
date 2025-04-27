package excopen.backend.servicesImpl;

import excopen.backend.entities.Tour;
import excopen.backend.entities.TourSchedule;
import excopen.backend.exceptions.ForbiddenException;
import excopen.backend.exceptions.NotFoundException;
import excopen.backend.iservices.TourScheduleService;
import excopen.backend.repositories.TourRepository;
import excopen.backend.repositories.TourScheduleRepository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourScheduleServiceImpl implements TourScheduleService {
    private final TourScheduleRepository scheduleRepository;
    private final TourRepository tourRepository;

    @Override
    @Transactional
    public TourSchedule createSchedule(TourSchedule schedule, Long userId) {
        Tour tour = tourRepository.findById(schedule.getTourId())
                .orElseThrow(() -> new NotFoundException("Tour not found"));
        if (!tour.getCreator().getId().equals(userId)) {
            throw new ForbiddenException("Cannot create schedule for another guide");
        }
        return scheduleRepository.save(schedule);
    }

    @Override
    @Transactional
    public TourSchedule updateSchedule(TourSchedule schedule, Long userId) {
        TourSchedule existing = scheduleRepository.findById(schedule.getId())
                .orElseThrow(() -> new NotFoundException("Schedule not found"));
        Tour tour = tourRepository.findById(existing.getTourId())
                .orElseThrow(() -> new NotFoundException("Tour not found"));
        if (!tour.getCreator().getId().equals(userId)) {
            throw new ForbiddenException("Cannot update schedule for another guide");
        }
        existing.setStartDate(schedule.getStartDate());
        existing.setEndDate(schedule.getEndDate());
        existing.setTime(schedule.getTime());
        existing.setRepeatInterval(schedule.getRepeatInterval());
        return scheduleRepository.save(existing);
    }

    @Override
    @Transactional
    public void deleteSchedule(Long scheduleId, Long userId) {
        TourSchedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundException("Schedule not found"));
        Tour tour = tourRepository.findById(schedule.getTourId())
                .orElseThrow(() -> new NotFoundException("Tour not found"));
        if (!tour.getCreator().getId().equals(userId)) {
            throw new ForbiddenException("Cannot delete schedule for another guide");
        }
        scheduleRepository.delete(schedule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourSchedule> getSchedulesForTour(Long tourId) {
        return scheduleRepository.findByTourId(tourId);
    }
}

