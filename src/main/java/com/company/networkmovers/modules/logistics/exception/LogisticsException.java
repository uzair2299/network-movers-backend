package com.company.networkmovers.modules.logistics.exception;

public class LogisticsException extends RuntimeException {
    public LogisticsException(String message) {
        super(message);
    }
    public LogisticsException(String message, Throwable cause) {
        super(message, cause);
    }
}
