package com.aditya.trucker.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when an operation is not allowed due to business rules.
 */
public class OperationNotAllowedException extends BaseException {
    public OperationNotAllowedException(String message) {
        super(message, HttpStatus.FORBIDDEN, "OPERATION_NOT_ALLOWED");
    }

    public OperationNotAllowedException(String resource, String operation) {
        super(String.format("Operation '%s' is not allowed on resource: %s", operation, resource),
              HttpStatus.FORBIDDEN,
              "OPERATION_NOT_ALLOWED");
    }
}
