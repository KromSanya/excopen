package excopen.backend.repositories;

import excopen.backend.entities.BookingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingSlotRepository extends JpaRepository<BookingSlot, Long> {

    List<BookingSlot> findByTourId(Long tourId);

    List<BookingSlot> findByTourIdAndStartDateTimeAfter(Long tourId, LocalDateTime after);

    boolean existsByTourId(Long tourId);
}
