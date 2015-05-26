package com.ivangusef.data.exception;

/**
 * Exception thrown by app when trying to read cache that does not exist.
 */
public class CacheNotFoundException extends Exception {

    public CacheNotFoundException() {
    }

    public CacheNotFoundException(final String detailMessage) {
        super(detailMessage);
    }

    public CacheNotFoundException(final String detailMessage, final Throwable throwable) {
        super(detailMessage, throwable);
    }

    public CacheNotFoundException(final Throwable throwable) {
        super(throwable);
    }
}
