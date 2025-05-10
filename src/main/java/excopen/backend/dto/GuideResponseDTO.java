package excopen.backend.dto;

import excopen.backend.constants.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GuideResponseDTO {

    private Long id;
    private String name;
    private String surname;
//    private LocalDateTime createdAt;
    private String info;
//  private String city;
    private Double rating = 0.0;
    private Integer ratingCount = 0;
    private ContactDTO contacts;
    private String avatar;
//    private List<String> tags;

}
