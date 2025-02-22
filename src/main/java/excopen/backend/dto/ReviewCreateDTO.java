package excopen.backend.dto;

import lombok.Data;

import jakarta.validation.constraints.*;

@Data
public class ReviewCreateDTO {
    @NotNull(message = "Tour ID is required")
    private Long tourId;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;

    @NotBlank(message = "Review text cannot be empty")
    @Size(max = 2000, message = "Review text must be less than 2000 characters")
    private String reviewText;
}
