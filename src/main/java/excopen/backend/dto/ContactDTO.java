package excopen.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactDTO {
    private String vk;
    private String telegram;

    @NotBlank(message = "Номер телефона обязателен")
    private String phone;
}
