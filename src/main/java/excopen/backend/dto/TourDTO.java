package excopen.backend.dto;

import excopen.backend.entities.Description;
import excopen.backend.entities.Tour;
import lombok.Data;

@Data
public class TourDTO {
    private Tour tour;
    private Description description;
}

