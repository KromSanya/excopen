package excopen.backend.mapper;

import excopen.backend.dto.BookingResponse;
import excopen.backend.dto.CreateBookingRequest;
import excopen.backend.entities.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "participants", source = "groupCapacity")
    Booking toEntity(CreateBookingRequest request);

    @Mapping(source = "participants", target = "groupCapacity")
    BookingResponse toResponse(Booking booking);
}

