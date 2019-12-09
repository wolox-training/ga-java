package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Represents a Exception for users.
 * @author German Asprino
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND , reason = "User Already Exist")
public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}