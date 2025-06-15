package excopen.backend.controllers;

import excopen.backend.dto.BookingSlotResponse;
import excopen.backend.dto.CreateBookingSlotRequest;
import excopen.backend.dto.CreateBookingSlotRequestBadVersion;
import excopen.backend.entities.BookingSlot;
import excopen.backend.entities.User;
import excopen.backend.iservices.BookingSlotService;
import excopen.backend.mapper.BookingSlotMapper;
import excopen.backend.security.CurrentUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/booking-slots")
@RequiredArgsConstructor
public class BookingSlotController {

    private final BookingSlotService slotService;
    private final BookingSlotMapper slotMapper;

//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @ResponseStatus(HttpStatus.CREATED)
//    public List<BookingSlotResponse> createSlots(
//            @Valid @RequestPart("slots") List<CreateBookingSlotRequest> requests,
//            @CurrentUser User user
//    ) {
//        List<BookingSlot> slots = requests.stream()
//                .map(slotMapper::toEntity)
//                .collect(Collectors.toList());
//
//        List<BookingSlot> createdSlots = slotService.createSlots(slots, user.getId());
//
//        return createdSlots.stream()
//                .map(slotMapper::toResponse)
//                .collect(Collectors.toList());
//    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<BookingSlotResponse> createSlots(
            @RequestBody @Valid List<CreateBookingSlotRequestBadVersion> requests,
            @CurrentUser User user) {

        // Ручная проверка на пустой список
        if (requests == null || requests.isEmpty()) {
            throw new IllegalArgumentException("Список слотов не может быть пустым");
        }

        List<BookingSlot> slots = requests.stream()
                .map(req -> {
                    BookingSlot slot = new BookingSlot();
                    slot.setTourId(req.getTourId());
                    slot.setStartDateTime(req.getDateTime());
                    slot.setMaxParticipants(req.getGroupCapacity());
                    slot.setAvailableSlots(req.getGroupCapacity());
                    return slot;
                })
                .collect(Collectors.toList());

        List<BookingSlot> createdSlots = slotService.createSlots(slots, user.getId());
        return createdSlots.stream()
                .map(slotMapper::toResponse)
                .collect(Collectors.toList());
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
