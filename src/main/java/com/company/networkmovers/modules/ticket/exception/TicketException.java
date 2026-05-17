package com.company.networkmovers.modules.ticket.exception;

public class TicketException extends RuntimeException {
    public TicketException(String message) {
        super(message);
    }
    public TicketException(String message, Throwable cause) {
        super(message, cause);
    }
}
