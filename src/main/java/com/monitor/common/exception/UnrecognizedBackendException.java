package com.monitor.common.exception;

public class UnrecognizedBackendException extends RuntimeException {
    /**
     * Constructor for UnrecognizedBackendException with a message.
     *
     * @param message The exception message
     */
    public UnrecognizedBackendException(final String message) {
        super(message);
    }

    /**
     * Default constructor for UnrecognizedBackendException.
     */
    public UnrecognizedBackendException() {
    }
}
