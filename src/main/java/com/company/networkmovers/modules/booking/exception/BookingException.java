package com.company.networkmovers.modules.booking.exception;

public class BookingException extends RuntimeException {
    public BookingException(String message) {
        super(message);
    }
    public BookingException(String message, Throwable cause) {
        super(message, cause);
    }
}
