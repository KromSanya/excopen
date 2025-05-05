package excopen.backend.dto;

import excopen.backend.constants.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
//    private LocalDateTime createdAt;
    private List<String> tags;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String info;
//    private String city;
    private Double rating = 0.0;
    private Integer ratingCount = 0;
    private ContactDTO contacts;
    private String avatar;

}