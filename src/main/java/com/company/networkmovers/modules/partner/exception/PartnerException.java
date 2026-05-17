package com.company.networkmovers.modules.partner.exception;

public class PartnerException extends RuntimeException {
    public PartnerException(String message) {
        super(message);
    }
    public PartnerException(String message, Throwable cause) {
        super(message, cause);
    }
}
