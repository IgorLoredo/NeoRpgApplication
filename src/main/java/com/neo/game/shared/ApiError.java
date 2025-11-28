package com.neo.game.shared;

import java.time.Instant;

public class ApiError {
    private final Instant timestamp = Instant.now();
    private final String message;

    public ApiError(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
