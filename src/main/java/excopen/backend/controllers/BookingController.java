package excopen.backend.controllers;


import excopen.backend.dto.BookingResponse;
import excopen.backend.dto.CreateBookingRequest;
import excopen.backend.entities.Booking;
import excopen.backend.entities.User;
import excopen.backend.iservices.BookingService;
import excopen.backend.mapper.BookingMapper;
import excopen.backend.security.CurrentUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse createBooking(
            @Valid @ModelAttribute CreateBookingRequest request,
            @CurrentUser User user
    ) {
        Booking booking = bookingMapper.toEntity(request);
        booking.setUserId(user.getId());

        Booking created = bookingService.createBooking(booking);

        return bookingMapper.toResponse(created);
    }

    @DeleteMapping("/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelBooking(
            @PathVariable Long bookingId,
            @CurrentUser User user
    ) {
        bookingService.cancelBooking(bookingId, user.getId());
    }

    @GetMapping("/me")
    public List<BookingResponse> getUserBookings(@CurrentUser User user) {
        return bookingService.getBookingsByUserId(user.getId()).stream()
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());
    }
}

