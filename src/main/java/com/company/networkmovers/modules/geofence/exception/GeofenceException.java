package com.company.networkmovers.modules.geofence.exception;

public class GeofenceException extends RuntimeException {
    public GeofenceException(String message) {
        super(message);
    }
    public GeofenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
