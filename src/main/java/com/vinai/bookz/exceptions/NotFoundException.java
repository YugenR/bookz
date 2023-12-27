package com.vinai.bookz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NotFoundException extends Throwable {

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Book not found"
    )
    public static class BookNotFound extends RuntimeException {}


}
