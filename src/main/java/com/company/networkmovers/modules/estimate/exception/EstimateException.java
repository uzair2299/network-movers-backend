package com.company.networkmovers.modules.estimate.exception;

public class EstimateException extends RuntimeException {
    public EstimateException(String message) {
        super(message);
    }
    public EstimateException(String message, Throwable cause) {
        super(message, cause);
    }
}
