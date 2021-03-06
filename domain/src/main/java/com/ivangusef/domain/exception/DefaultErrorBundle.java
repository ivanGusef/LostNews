package com.ivangusef.domain.exception;

/**
 * Wrapper around Exceptions used to manage default errors.
 */
public class DefaultErrorBundle implements ErrorBundle {

    private final Exception exception;

    public DefaultErrorBundle(final Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        return exception != null ? exception.getMessage() : "";
    }
}
