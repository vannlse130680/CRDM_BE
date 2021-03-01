package com.capstone.crdm.exception;

public class CrdmUnauthorizedException extends CrdmException {

    public CrdmUnauthorizedException(String message) {
        super(message);
    }

    public CrdmUnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CrdmUnauthorizedException(Throwable cause) {
        super(cause);
    }

    protected CrdmUnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
