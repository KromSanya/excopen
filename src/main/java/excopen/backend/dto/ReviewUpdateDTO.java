package excopen.backend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ReviewUpdateDTO {
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;

    @NotBlank(message = "Review text cannot be empty")
    @Size(max = 2000, message = "Review text must be less than 2000 characters")
    private String reviewText;
}
