package excopen.backend.controllers;

import excopen.backend.dto.CreateTourScheduleRequest;
import excopen.backend.dto.TourScheduleResponse;
import excopen.backend.entities.TourSchedule;
import excopen.backend.entities.User;
import excopen.backend.iservices.TourScheduleService;
import excopen.backend.mapper.TourScheduleMapper;
import excopen.backend.security.CurrentUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tour-schedules")
@RequiredArgsConstructor
public class TourScheduleController {

    private final TourScheduleService scheduleService;
    private final TourScheduleMapper scheduleMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TourScheduleResponse createSchedule(
            @Valid @RequestBody CreateTourScheduleRequest request,
            @CurrentUser User user
    ) {
        TourSchedule schedule = scheduleMapper.toEntity(request);
        TourSchedule created = scheduleService.createSchedule(schedule, user.getId());
        return scheduleMapper.toResponse(created);
    }

    @GetMapping("/tour/{tourId}")
    public List<TourScheduleResponse> getSchedulesForTour(@PathVariable Long tourId) {
        return scheduleService.getSchedulesForTour(tourId).stream()
                .map(scheduleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PutMapping("/{scheduleId}")
    public TourScheduleResponse updateSchedule(
            @PathVariable Long scheduleId,
            @Valid @RequestBody CreateTourScheduleRequest request,
            @CurrentUser User user
    ) {
        TourSchedule schedule = scheduleMapper.toEntity(request);
        schedule.setId(scheduleId);
        TourSchedule updated = scheduleService.updateSchedule(schedule, user.getId());
        return scheduleMapper.toResponse(updated);
    }

    @DeleteMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(
            @PathVariable Long scheduleId,
            @CurrentUser User user
    ) {
        scheduleService.deleteSchedule(scheduleId, user.getId());
    }
}

