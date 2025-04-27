package excopen.backend.repositories;

import excopen.backend.entities.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourScheduleRepository extends JpaRepository<TourSchedule, Long> {

    List<TourSchedule> findByTourId(Long tourId);

    boolean existsByTourId(Long tourId);
}
