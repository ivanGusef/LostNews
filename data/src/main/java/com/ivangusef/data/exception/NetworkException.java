package com.ivangusef.data.exception;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public class NetworkException extends Exception {

    public NetworkException() {
    }

    public NetworkException(final String detailMessage) {
        super(detailMessage);
    }

    public NetworkException(final String detailMessage, final Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NetworkException(final Throwable throwable) {
        super(throwable);
    }
}
