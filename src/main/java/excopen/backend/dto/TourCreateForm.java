package excopen.backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class TourCreateForm {

    @Valid
    @NotNull(message = "Основные данные тура обязательны")
    private TourCreateDTO tour;

//    @NotEmpty(message = "Необходимо загрузить хотя бы одно изображение")
    private List<MultipartFile> images;
}
