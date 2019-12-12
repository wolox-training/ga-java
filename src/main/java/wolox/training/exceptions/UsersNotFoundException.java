package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Represents a Exception for users.
 * @author German Asprino
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND , reason = "User Not Found")
public class UsersNotFoundException extends RuntimeException {

    public UsersNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}