package com.monitor.common.exception;

public class GiteeRequestException extends RuntimeException {
    /**
     * Constructor for GiteeRequestException with a message.
     *
     * @param message The exception message
     */
    public GiteeRequestException(final String message) {
        super(message);
    }

    /**
     * Default constructor for GiteeRequestException.
     */
    public GiteeRequestException() {
    }
}
