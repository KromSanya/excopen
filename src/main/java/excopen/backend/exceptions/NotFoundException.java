package excopen.backend.exceptions;

/**
 * Выбрасывается, когда запрошенная сущность не найдена.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
