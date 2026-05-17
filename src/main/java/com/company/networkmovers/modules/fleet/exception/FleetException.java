package com.company.networkmovers.modules.fleet.exception;

public class FleetException extends RuntimeException {
    public FleetException(String message) {
        super(message);
    }
    public FleetException(String message, Throwable cause) {
        super(message, cause);
    }
}
