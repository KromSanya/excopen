package excopen.backend.dto.guidepromotion;

import excopen.backend.dto.ContactDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GuideRequestDto {

    @NotBlank(message = "Описание не должно быть пустым")
    private String info;

//    @NotBlank(message = "Город обязателен")
//    private String city;
    private ContactDTO contacts;


}

