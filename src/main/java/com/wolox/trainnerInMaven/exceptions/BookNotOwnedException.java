package com.wolox.trainnerInMaven.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Represents a Exception for book.
 * @author German Asprino
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND , reason = "Book Not Owned By User")
public class BookNotOwnedException extends RuntimeException {

    public BookNotOwnedException(String message, Throwable cause) {
        super(message, cause);
    }
}
