package excopen.backend.exceptions;

/**
 * Выбрасывается при некорректных данных запроса или нарушении бизнес-логики.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
