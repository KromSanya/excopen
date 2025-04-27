package excopen.backend.iservices;

import excopen.backend.entities.Booking;
import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);
    void cancelBooking(Long bookingId, Long userId);
    List<Booking> getBookingsByUserId(Long userId);
}
