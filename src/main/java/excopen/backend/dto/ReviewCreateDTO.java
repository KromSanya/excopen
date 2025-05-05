package excopen.backend.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ReviewCreateDTO {

    @NotNull(message = "Tour ID is required")
    private Long tourId;

    @NotNull(message = "Rating is required")
    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Rating must be at most 10.0")
    private Double rating;

    @NotNull(message = "withChildren is required")
    private Boolean withChildren;
    @NotNull(message = "personCount is required")
    private Integer personCount;

    @NotBlank(message = "Review text cannot be empty")
    @Size(max = 2000, message = "Review text must be less than 2000 characters")
    private String positiveText;

    @NotBlank(message = "Review text cannot be empty")
    @Size(max = 2000, message = "Review text must be less than 2000 characters")
    private String negativeText;

//    private List<MultipartFile> images;
}
