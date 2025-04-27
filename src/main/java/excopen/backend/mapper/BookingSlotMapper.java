package excopen.backend.mapper;


import excopen.backend.dto.BookingSlotResponse;
import excopen.backend.dto.CreateBookingSlotRequest;
import excopen.backend.entities.BookingSlot;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingSlotMapper {
    BookingSlotMapper INSTANCE = Mappers.getMapper(BookingSlotMapper.class);

    BookingSlot toEntity(CreateBookingSlotRequest request);

    BookingSlotResponse toResponse(BookingSlot slot);
}
