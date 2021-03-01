package com.capstone.crdm.exception;

import lombok.Getter;

@Getter
public class CrdmException extends RuntimeException {

    protected final ExceptionContent content = new ExceptionContent();

    public CrdmException(String message) {
        super(message);
        this.getContent().setMessage(this.getMessage());
    }

    public CrdmException(String message, Throwable cause) {
        super(message, cause);
        this.getContent().setMessage(this.getMessage());
    }

    public CrdmException(Throwable cause) {
        super(cause);
        this.getContent().setMessage(this.getMessage());
    }

    protected CrdmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.getContent().setMessage(this.getMessage());
    }

}
