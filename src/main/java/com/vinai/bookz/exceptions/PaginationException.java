package com.vinai.bookz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PaginationException extends Throwable {

    @ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "Invalid sorting argument"
    )
    public static class InvalidSortingArgumentsException extends RuntimeException {}

}
