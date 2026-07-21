package com.store.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PasswordUnmatchException extends RuntimeException {
    public PasswordUnmatchException(String message) {
        super(message);
    }
}
