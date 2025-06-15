package excopen.backend.servicesImpl;

import excopen.backend.entities.BookingSlot;
import excopen.backend.entities.Tour;
import excopen.backend.exceptions.ForbiddenException;
import excopen.backend.exceptions.NotFoundException;
import excopen.backend.iservices.BookingSlotService;
import excopen.backend.repositories.BookingSlotRepository;
import excopen.backend.repositories.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingSlotServiceImpl implements BookingSlotService {
    private final BookingSlotRepository slotRepository;
    private final TourRepository tourRepository;

    @Override
    @Transactional
    public List<BookingSlot> createSlots(List<BookingSlot> slots, Long userId) {
        Set<Long> tourIds = slots.stream()
                .map(BookingSlot::getTourId)
                .collect(Collectors.toSet());

        Map<Long, Tour> tours = tourRepository.findAllById(tourIds).stream()
                .collect(Collectors.toMap(Tour::getId, tour -> tour));

        for (BookingSlot slot : slots) {
            Tour tour = tours.get(slot.getTourId());
            if (tour == null) {
                throw new NotFoundException("Tour not found for ID: " + slot.getTourId());
            }
            if (!tour.getCreator().getId().equals(userId)) {
                throw new ForbiddenException("Cannot create slots for another guide\n");
            }
        }
        return slotRepository.saveAll(slots);
    }

    @Override
    @Transactional
    public BookingSlot createSlot(BookingSlot slot, Long userId) {
        Tour tour = tourRepository.findById(slot.getTourId())
                .orElseThrow(() -> new NotFoundException("Tour not found"));
        if (!tour.getCreator().getId().equals(userId)) {
            throw new ForbiddenException("Cannot create slot for another guide\n");
        }
        return slotRepository.save(slot);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingSlot> getAvailableSlotsForTour(Long tourId) {
        return slotRepository.findByTourIdAndStartDateTimeAfter(tourId, java.time.LocalDateTime.now());
    }

    @Override
    @Transactional
    public void deleteSlot(Long slotId, Long userId) {
        BookingSlot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new NotFoundException("Slot not found"));
        Tour tour = tourRepository.findById(slot.getTourId())
                .orElseThrow(() -> new NotFoundException("Tour not found"));
        if (!tour.getCreator().getId().equals(userId)) {
            throw new ForbiddenException("Cannot delete slot for another guide");
        }
        slotRepository.delete(slot);
    }
}

