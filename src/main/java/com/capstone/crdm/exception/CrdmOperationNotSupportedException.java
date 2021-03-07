package com.capstone.crdm.exception;

import org.springframework.http.HttpStatus;

public class CrdmOperationNotSupportedException extends CrdmException {

    public CrdmOperationNotSupportedException(String message) {
        super(message);
        this.content.setErrorCode(HttpStatus.BAD_REQUEST.value());
    }

    public CrdmOperationNotSupportedException(String message, Throwable cause) {
        super(message, cause);
        this.content.setErrorCode(HttpStatus.BAD_REQUEST.value());
    }

    public CrdmOperationNotSupportedException(Throwable cause) {
        super(cause);
        this.content.setErrorCode(HttpStatus.BAD_REQUEST.value());
    }

    protected CrdmOperationNotSupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.content.setErrorCode(HttpStatus.BAD_REQUEST.value());
    }

}
