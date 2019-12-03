package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Represents a Exception for book.
 * @author German Asprino
 */

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Book Id mismatch")
public class BookIdMismatchException extends RuntimeException {

    public BookIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
