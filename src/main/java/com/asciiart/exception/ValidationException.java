package com.asciiart.exception;

/**
 * Represent a non-retryable validation exception.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
