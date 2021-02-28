package com.capstone.crdm.exception;

public class CrdmIllegalAccessException extends CrdmException {

    public CrdmIllegalAccessException(String message) {
        super(message);
        this.getContent().setErrorCode(403);
    }

    public CrdmIllegalAccessException(String message, Throwable cause) {
        super(message, cause);
        this.getContent().setErrorCode(403);
    }

    public CrdmIllegalAccessException(Throwable cause) {
        super(cause);
        this.getContent().setErrorCode(403);
    }

    protected CrdmIllegalAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.getContent().setErrorCode(403);
    }

}
