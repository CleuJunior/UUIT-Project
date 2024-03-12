package com.interion.uuit.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ErrorInfoFactory {

    public ErrorInfoJson from(HttpStatus status, RuntimeException exception) {
        return new ErrorInfoJson(
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage(),
                Instant.now()
        );
    }


}
