package com.aditya.trucker.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Base exception class for the application.
 * All custom exceptions should extend this class.
 */
public class BaseException extends RuntimeException {
    private final HttpStatus status;
    private final LocalDateTime timestamp;
    private final String errorCode;

    public BaseException(String message, HttpStatus status, String errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
