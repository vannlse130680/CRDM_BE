package com.capstone.crdm.exception;

public class CrdmResourceNotFoundException extends CrdmException {

    public CrdmResourceNotFoundException(String message) {
        super(message);
        this.getContent().setErrorCode(404);
    }

    public CrdmResourceNotFoundException(Throwable cause) {
        super(cause);
        this.getContent().setErrorCode(404);
    }

}
