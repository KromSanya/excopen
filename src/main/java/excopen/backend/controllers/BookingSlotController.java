package excopen.backend.controllers;

import excopen.backend.dto.BookingSlotResponse;
import excopen.backend.dto.CreateBookingSlotRequest;
import excopen.backend.entities.BookingSlot;
import excopen.backend.entities.User;
import excopen.backend.iservices.BookingSlotService;
import excopen.backend.mapper.BookingSlotMapper;
import excopen.backend.security.CurrentUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/booking-slots")
@RequiredArgsConstructor
public class BookingSlotController {

    private final BookingSlotService slotService;
    private final BookingSlotMapper slotMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingSlotResponse createSlot(
            @Valid @RequestBody CreateBookingSlotRequest request,
            @CurrentUser User user
    ) {
        BookingSlot slot = slotMapper.toEntity(request);
        BookingSlot created = slotService.createSlot(slot, user.getId());
        return slotMapper.toResponse(created);
    }

    @GetMapping("/tour/{tourId}")
    public List<BookingSlotResponse> getAvailableSlots(@PathVariable Long tourId) {
        return slotService.getAvailableSlotsForTour(tourId).stream()
                .map(slotMapper::toResponse)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{slotId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSlot(
            @PathVariable Long slotId,
            @CurrentUser User user
    ) {
        slotService.deleteSlot(slotId, user.getId());
    }
}
