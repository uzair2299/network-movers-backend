package com.company.networkmovers.modules.maps.exception;

public class MapsException extends RuntimeException {
    public MapsException(String message) {
        super(message);
    }
    public MapsException(String message, Throwable cause) {
        super(message, cause);
    }
}
