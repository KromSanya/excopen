package excopen.backend.mapper;

import excopen.backend.dto.CreateTourScheduleRequest;
import excopen.backend.dto.TourScheduleResponse;
import excopen.backend.entities.TourSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TourScheduleMapper {
    TourScheduleMapper INSTANCE = Mappers.getMapper(TourScheduleMapper.class);

    TourSchedule toEntity(CreateTourScheduleRequest request);

    TourScheduleResponse toResponse(TourSchedule schedule);
}
