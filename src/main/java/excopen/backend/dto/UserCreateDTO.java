package excopen.backend.dto;

import lombok.Data;

@Data
public class UserCreateDTO {
    private String googleId;
    private String name;
    private String surname;
    private String email;
}
