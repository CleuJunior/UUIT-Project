package com.interion.uuit.exceptions;

import org.springframework.http.HttpStatus;

public class FullCapaticyException extends BaseException {

    private static final String MESSAGE = "This discipline is already at maximum capacity";

    public FullCapaticyException() {
        super(MESSAGE);
    }

    public FullCapaticyException(Throwable cause) {
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
