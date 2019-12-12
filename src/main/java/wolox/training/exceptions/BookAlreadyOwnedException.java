package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Represents a Exception for book.
 * @author German Asprino
 */

@ResponseStatus(code = HttpStatus.BAD_REQUEST , reason = "Book Already Owned")
public class BookAlreadyOwnedException extends RuntimeException {

    public BookAlreadyOwnedException(String message, Throwable cause) {
        super(message, cause);
    }
}
