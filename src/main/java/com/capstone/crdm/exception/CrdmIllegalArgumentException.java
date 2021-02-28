package com.capstone.crdm.exception;

public class CrdmIllegalArgumentException extends CrdmException {

    public CrdmIllegalArgumentException(String message) {
        super(message);
        this.getContent().setErrorCode(400);
    }

    public CrdmIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
        this.getContent().setErrorCode(400);
    }

    public CrdmIllegalArgumentException(Throwable cause) {
        super(cause);
        this.getContent().setErrorCode(400);
    }

    protected CrdmIllegalArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.getContent().setErrorCode(400);
    }

}
