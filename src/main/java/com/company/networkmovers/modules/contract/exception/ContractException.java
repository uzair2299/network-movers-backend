package com.company.networkmovers.modules.contract.exception;

public class ContractException extends RuntimeException {
    public ContractException(String message) {
        super(message);
    }
    public ContractException(String message, Throwable cause) {
        super(message, cause);
    }
}
