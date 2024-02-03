package com.interion.uuit.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int httpStatusValue() {
        return HttpStatus.NOT_FOUND.value();
    }

    @Override
    public String getReasonPhrase() {
        return HttpStatus.NOT_FOUND.getReasonPhrase();
    }
}
