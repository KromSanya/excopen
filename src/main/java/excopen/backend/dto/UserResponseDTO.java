package excopen.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int[] preferencesVector;
}