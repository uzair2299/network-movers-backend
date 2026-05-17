package com.company.networkmovers.modules.realtime.exception;

public class RealtimeException extends RuntimeException {
    public RealtimeException(String message) {
        super(message);
    }
    public RealtimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
