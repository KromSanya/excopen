package excopen.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class DescriptionDTO {

    @NotBlank(message = "Основная информация обязательна")
    private String mainInfo;

    @NotBlank(message = "Информация об ожидании обязательна")
    private String whatToExpect;

    private List<String> places;

    private List<String> topics;

    @NotBlank(message = "Организационные детали обязательны")
    private String orgDetails;

    @NotBlank(message = "Место встречи обязательно")
    private String meetingPlace;
}