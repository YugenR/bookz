package com.vinai.bookz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BadRequestException extends Throwable {

    @ResponseStatus(
            value = HttpStatus.CONFLICT,
            reason = "A book with this ISBN already exists"
    )
    public static class IsbnAlreadyExists extends RuntimeException {}

    @ResponseStatus(
            value = HttpStatus.CONFLICT,
            reason = "A user with this email already exists"
    )
    public static class EmailAlreadyExists extends RuntimeException {}


}
