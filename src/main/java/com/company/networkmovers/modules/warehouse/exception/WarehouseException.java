package com.company.networkmovers.modules.warehouse.exception;

public class WarehouseException extends RuntimeException {
    public WarehouseException(String message) {
        super(message);
    }
    public WarehouseException(String message, Throwable cause) {
        super(message, cause);
    }
}
