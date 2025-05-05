package excopen.backend.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class SearchParamsDTO {

    private LocationDTO location;
    private DateRangeDTO date;
    private String accessibility;
    private Boolean byCity;

    @Data
    public static class LocationDTO {
        private Long id;
        private String city;
        private String region;
        private String country;
        private String imageUrl;
        private Integer tourCount;
    }

    @Data
    public static class DateRangeDTO {
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private LocalDateTime from;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private LocalDateTime to;
    }
}
