package excopen.backend.repositories;

import excopen.backend.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByBookingSlotId(Long slotId);

    List<Booking> findByUserId(Long userId);
}
