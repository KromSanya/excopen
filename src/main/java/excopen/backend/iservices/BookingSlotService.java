package excopen.backend.iservices;

import excopen.backend.entities.BookingSlot;
import java.util.List;

public interface BookingSlotService {
    BookingSlot createSlot(BookingSlot slot, Long userId);
    List<BookingSlot> getAvailableSlotsForTour(Long tourId);
    void deleteSlot(Long slotId, Long userId);
}