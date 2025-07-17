package com.aditya.trucker.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Exception thrown when validation of a request fails.
 */
public class ValidationException extends BaseException {
    private final Map<String, String> validationErrors;

    public ValidationException(String message, Map<String, String> validationErrors) {
        super(message, HttpStatus.BAD_REQUEST, "VALIDATION_ERROR");
        this.validationErrors = validationErrors;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }
}
