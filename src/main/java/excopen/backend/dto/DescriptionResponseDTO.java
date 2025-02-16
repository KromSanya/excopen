package excopen.backend.dto;

import lombok.Data;

@Data
public class DescriptionResponseDTO {

    private Long id;
    private Long tourId;
    private String mainInfo;
    private String whatToExpect;
    private String orgDetails;
    private String meetingPlace;
}
