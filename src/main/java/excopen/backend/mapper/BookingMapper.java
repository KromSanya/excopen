package excopen.backend.mapper;

import excopen.backend.dto.BookingResponse;
import excopen.backend.dto.CreateBookingRequest;
import excopen.backend.entities.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    Booking toEntity(CreateBookingRequest request);

    BookingResponse toResponse(Booking booking);
}

