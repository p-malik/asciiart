package com.asciiart.exception;

/**
 * Represent a non-retryable validation exception.
 */
public class AsciiArtRuntimeException extends RuntimeException {
    public AsciiArtRuntimeException(String message) {
        super(message);
    }

    public AsciiArtRuntimeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
