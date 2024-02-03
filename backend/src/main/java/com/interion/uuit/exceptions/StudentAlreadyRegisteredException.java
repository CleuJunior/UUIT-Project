package com.interion.uuit.exceptions;

import org.springframework.http.HttpStatus;

public class StudentAlreadyRegisteredException extends BaseException {

    private static final String MESSAGE = "Student already registered";

    public StudentAlreadyRegisteredException() {
        super(MESSAGE);
    }

    public StudentAlreadyRegisteredException(Throwable cause) {
        super(MESSAGE, cause);
    }

    @Override
    public int httpStatusValue() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getReasonPhrase() {
        return HttpStatus.BAD_REQUEST.getReasonPhrase();
    }
}
