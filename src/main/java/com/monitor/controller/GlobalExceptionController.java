package com.monitor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.monitor.common.exception.NotFoundPullRequestException;
import com.monitor.common.exception.UnrecognizedMonitorException;

@RestControllerAdvice
public class GlobalExceptionController {
    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionController.class);

    /**
     * catch NotFoundPullRequestException.
     * @param e NotFoundPullRequestException.
     */
    @ExceptionHandler(NotFoundPullRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void exception(final NotFoundPullRequestException e) {
        LOGGER.error("not found pull request, cause: {}", e.getMessage());
    }

    /**
     * catch UnrecognizedMonitorException.
     * @param e UnrecognizedMonitorException.
     */
    @ExceptionHandler(UnrecognizedMonitorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void exception(final UnrecognizedMonitorException e) {
        LOGGER.error("un recognized monitor, cause: {}", e.getMessage());
    }

    /**
     * catch RuntimeException.
     * @param e RuntimeException.
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void exception(final RuntimeException e) {
        LOGGER.error("run time exception, cause: {}", e.getMessage());
    }

    /**
     * catch Exception.
     * @param e Exception.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void exception(final Exception e) {
        LOGGER.error("exception, cause: {}", e.getMessage());
    }
}
