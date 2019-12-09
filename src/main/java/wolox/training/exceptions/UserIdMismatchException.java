package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Represents a Exception for users.
 * @author German Asprino
 */

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "User Id mismatch")
public class UserIdMismatchException extends RuntimeException {

    public UserIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}