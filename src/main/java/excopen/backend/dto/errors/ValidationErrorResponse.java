package excopen.backend.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class ValidationErrorResponse {
    private String message;
    private String errorCode;
    private Instant timestamp;
    private List<ValidationError> errors;

    public ValidationErrorResponse(String message, String errorCode, List<ValidationError> errors) {
        this(message, errorCode, Instant.now(), errors);
    }
}
