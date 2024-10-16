package com.monitor.common.exception;

public class UnrecognizedMonitorException extends RuntimeException {
    /**
     * Constructor for UnrecognizedMonitorException with a message.
     *
     * @param message The exception message
     */
    public UnrecognizedMonitorException(final String message) {
        super(message);
    }

    /**
     * Default constructor for UnrecognizedMonitorException.
     */
    public UnrecognizedMonitorException() {
    }
}
