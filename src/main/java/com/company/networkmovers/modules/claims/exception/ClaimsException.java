package com.company.networkmovers.modules.claims.exception;

public class ClaimsException extends RuntimeException {
    public ClaimsException(String message) {
        super(message);
    }
    public ClaimsException(String message, Throwable cause) {
        super(message, cause);
    }
}
