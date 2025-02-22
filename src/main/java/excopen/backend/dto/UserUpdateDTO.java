package excopen.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateDTO {
    @NotBlank(message = "Имя не может быть пустым")
    private String name;
    @NotBlank(message = "Фамилия не может быть пустым")
    private String surname;
    private int[] preferencesVector;
}
