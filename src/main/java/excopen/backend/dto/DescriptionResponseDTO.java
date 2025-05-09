package excopen.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class DescriptionResponseDTO {

    private Long id;
    private Long tourId;
    private String info;
    private String whatToExpect;
    private List<String> places;

    private List<String> topics;
    private String orgDetails;
    private String meetingPlace;
}
