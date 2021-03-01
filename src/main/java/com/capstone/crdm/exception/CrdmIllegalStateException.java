package com.capstone.crdm.exception;

public class CrdmIllegalStateException extends CrdmException {

    public CrdmIllegalStateException(String message) {
        super(message);
        this.getContent().setErrorCode(500);
    }

    public CrdmIllegalStateException(String message, Throwable cause) {
        super(message, cause);
        this.getContent().setErrorCode(500);
    }

    public CrdmIllegalStateException(Throwable cause) {
        super(cause);
        this.getContent().setErrorCode(500);
    }

    protected CrdmIllegalStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.getContent().setErrorCode(500);
    }
}
