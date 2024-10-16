package com.monitor.common.exception;

public class NotFoundPullRequestException extends RuntimeException {
    /**
     * Constructor for NotFoundPullRequestException with a message.
     *
     * @param message The exception message
     */
    public NotFoundPullRequestException(final String message) {
        super(message);
    }

    /**
     * Default constructor for NotFoundPullRequestException.
     */
    public NotFoundPullRequestException() {
    }
}
