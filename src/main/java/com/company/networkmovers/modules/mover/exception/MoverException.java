package com.company.networkmovers.modules.mover.exception;

public class MoverException extends RuntimeException {
    public MoverException(String message) {
        super(message);
    }
    public MoverException(String message, Throwable cause) {
        super(message, cause);
    }
}
