package com.interion.uuit.exceptions;

import org.springframework.http.HttpStatus;

public class DisciplineClosedException extends BaseException {

    private static final String MESSAGE = "Discipline is no longer available";

    public DisciplineClosedException() {
        super(MESSAGE);
    }

    public DisciplineClosedException(Throwable cause) {
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
