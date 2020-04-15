package com.wolox.trainnerInMaven.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Represents a Exception for book.
 * @author German Asprino
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND , reason = "Book Id Mismatch")
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}