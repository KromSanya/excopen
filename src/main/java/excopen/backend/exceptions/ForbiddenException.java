package excopen.backend.exceptions;

/**
 * Выбрасывается, когда у пользователя нет прав на выполнение действия.
 */
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
