package com.capstone.crdm.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionContent {
    private String timestamp;
    private String message;
    private int errorCode;
    private String path;
}
