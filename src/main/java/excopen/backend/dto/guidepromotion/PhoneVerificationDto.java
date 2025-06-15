package excopen.backend.dto.guidepromotion;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PhoneVerificationDto {
    @NotBlank(message = "Номер телефона обязателен")
    private String phoneNumber;

    @NotBlank(message = "Код подтверждения обязателен")
    private String code;
}