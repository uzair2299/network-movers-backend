package com.company.networkmovers.modules.support.exception;

public class SupportException extends RuntimeException {
    public SupportException(String message) {
        super(message);
    }
    public SupportException(String message, Throwable cause) {
        super(message, cause);
    }
}
