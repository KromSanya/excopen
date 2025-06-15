package excopen.backend.dto.guidepromotion;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GuideApplicationRequest {
    @NotBlank(message = "Телефон обязателен")
    private String phone;

    @NotBlank(message = "Код подтверждения обязателен")
    private String code;

    @NotBlank(message = "Описание обязательно")
    private String info;

//    private String vk;
//
//    private String telegram;
//
//    // Геттер для проверки наличия контактов
//    public boolean hasContacts() {
//        return (vk != null && !vk.isBlank()) ||
//                (telegram != null && !telegram.isBlank());
//    }
}
