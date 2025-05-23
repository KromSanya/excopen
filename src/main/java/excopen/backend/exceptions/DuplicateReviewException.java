package excopen.backend.exceptions;


/**
 * Выбрасывается при попытке создать второй отзыв.
 */
public class DuplicateReviewException extends RuntimeException {
    public DuplicateReviewException(String message) {
        super(message);
    }
}
