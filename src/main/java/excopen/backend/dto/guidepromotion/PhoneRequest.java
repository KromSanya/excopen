package excopen.backend.dto.guidepromotion;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PhoneRequest {
    @NotBlank(message = "Телефон обязателен")
    private String phone;
}
