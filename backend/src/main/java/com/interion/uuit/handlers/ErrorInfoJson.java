package com.interion.uuit.handlers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record ErrorInfoJson(
        int status,
        @JsonProperty("status_message")
        String statusErrorMessage,
        @JsonProperty("error_message")
        String message,
        Instant timestamp
) {
        public ErrorInfoJson(int status, String statusErrorMessage, String message) {
            this(status, statusErrorMessage, message, Instant.now());
        }
}
