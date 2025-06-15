package excopen.backend.dto.guidepromotion;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PhoneVerificationRequest {
    @NotBlank(message = "Номер телефона обязателен")
    private String phoneNumber;
}
