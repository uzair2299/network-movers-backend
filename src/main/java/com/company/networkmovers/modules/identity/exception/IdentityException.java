package com.company.networkmovers.modules.identity.exception;

public class IdentityException extends RuntimeException {
    public IdentityException(String message) {
        super(message);
    }
    public IdentityException(String message, Throwable cause) {
        super(message, cause);
    }
}
