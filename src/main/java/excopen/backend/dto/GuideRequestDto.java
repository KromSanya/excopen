package excopen.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class GuideRequestDto {

    @NotBlank(message = "Описание не должно быть пустым")
    private String info;

    @NotBlank(message = "Город обязателен")
    private String city;
    private ContactDTO contacts;


}

