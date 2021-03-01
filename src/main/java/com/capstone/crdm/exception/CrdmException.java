package com.capstone.crdm.exception;

import lombok.Getter;

@Getter
public class CrdmException extends RuntimeException {

    protected final ExceptionContent content = new ExceptionContent();

    public CrdmException(String message) {
        super(message);
    }

    public CrdmException(String message, Throwable cause) {
        super(message, cause);
    }

    public CrdmException(Throwable cause) {
        super(cause);
    }

    protected CrdmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
