package com.company.networkmovers.modules.hr.exception;

public class HrException extends RuntimeException {
    public HrException(String message) {
        super(message);
    }
    public HrException(String message, Throwable cause) {
        super(message, cause);
    }
}
