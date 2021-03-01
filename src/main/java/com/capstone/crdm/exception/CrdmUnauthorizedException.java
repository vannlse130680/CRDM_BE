package com.capstone.crdm.exception;

public class CrdmUnauthorizedException extends CrdmException {

    public CrdmUnauthorizedException(String message) {
        super(message);
        this.getContent().setErrorCode(401);
    }

    public CrdmUnauthorizedException(String message, Throwable cause) {
        super(message, cause);
        this.getContent().setErrorCode(401);
    }

    public CrdmUnauthorizedException(Throwable cause) {
        super(cause);
        this.getContent().setErrorCode(401);
    }

    protected CrdmUnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.getContent().setErrorCode(401);
    }

}
