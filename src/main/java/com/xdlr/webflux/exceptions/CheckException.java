package com.xdlr.webflux.exceptions;

import lombok.Data;

@Data
public class CheckException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    private String fieldName;

    private String fieldValue;

    public CheckException() {
        super();
    }

    public CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public CheckException(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
