package com.company.networkmovers.modules.dispatch.exception;

public class DispatchException extends RuntimeException {
    public DispatchException(String message) {
        super(message);
    }
    public DispatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
