package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Book Already Exist")
public class BookAlreadyExistException extends RuntimeException {

    public BookAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
