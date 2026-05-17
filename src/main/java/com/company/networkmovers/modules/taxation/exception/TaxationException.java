package com.company.networkmovers.modules.taxation.exception;

public class TaxationException extends RuntimeException {
    public TaxationException(String message) {
        super(message);
    }
    public TaxationException(String message, Throwable cause) {
        super(message, cause);
    }
}
