package excopen.backend.servicesImpl;

import excopen.backend.entities.Booking;
import excopen.backend.entities.Tour;
import excopen.backend.exceptions.NotFoundException;
import excopen.backend.iservices.BookingService;
import excopen.backend.repositories.BookingRepository;
import excopen.backend.repositories.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final TourRepository tourRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, TourRepository tourRepository) {
        this.bookingRepository = bookingRepository;
        this.tourRepository = tourRepository;
    }

    @Transactional
    public Booking createBooking(Booking booking) {
        Tour tour = tourRepository.findById(booking.getTourId())
                .orElseThrow(() -> new NotFoundException("Tour not found"));

        int requiredSeats = booking.getParticipants();
        int availableSeats = tour.getFreeSeats();

        if (availableSeats < requiredSeats) {
            throw new IllegalArgumentException("Not enough available seats");
        }

        tour.setFreeSeats(availableSeats - requiredSeats);
        tourRepository.save(tour);

        return bookingRepository.save(booking);
    }

    @Transactional
    public void cancelBooking(Long bookingId, Long userId) {
        Booking booking = bookingRepository.findByIdAndUserId(bookingId, userId)
                .orElseThrow(() -> new NotFoundException("Booking not found"));

        Tour tour = tourRepository.findById(booking.getTourId())
                .orElseThrow(() -> new NotFoundException("Tour not found"));

        tour.setFreeSeats(tour.getFreeSeats() + booking.getParticipants());
        tourRepository.save(tour);

        bookingRepository.delete(booking);
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}