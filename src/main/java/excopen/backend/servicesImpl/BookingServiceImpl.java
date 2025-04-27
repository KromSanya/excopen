package excopen.backend.servicesImpl;

import excopen.backend.constants.BookingStatus;
import excopen.backend.entities.Booking;
import excopen.backend.entities.BookingSlot;
import excopen.backend.exceptions.BadRequestException;
import excopen.backend.exceptions.ForbiddenException;
import excopen.backend.exceptions.NotFoundException;
import excopen.backend.iservices.BookingService;
import excopen.backend.repositories.BookingRepository;
import excopen.backend.repositories.BookingSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingSlotRepository slotRepository;

    @Override
    @Transactional
    public Booking createBooking(Booking booking) {
        BookingSlot slot = slotRepository.findById(booking.getBookingSlotId())
                .orElseThrow(() -> new NotFoundException("Booking slot not found"));
        if (!slot.getAvailableSlots().equals(booking.getParticipants())) {
            if (slot.getAvailableSlots() < booking.getParticipants()) {
                throw new BadRequestException("Not enough available slots");
            }
        }
        slot.setAvailableSlots(slot.getAvailableSlots() - booking.getParticipants());
        slotRepository.save(slot);

        booking.setStatus(BookingStatus.PENDING);
        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public void cancelBooking(Long bookingId, Long userId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking not found"));
        if (!booking.getUserId().equals(userId)) {
            throw new ForbiddenException("Cannot cancel others' bookings");
        }
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            return;
        }
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        BookingSlot slot = slotRepository.findById(booking.getBookingSlotId())
                .orElseThrow(() -> new NotFoundException("Booking slot not found"));
        slot.setAvailableSlots(slot.getAvailableSlots() + booking.getParticipants());
        slotRepository.save(slot);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}