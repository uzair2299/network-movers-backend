package com.company.networkmovers.modules.filemanagement.exception;

public class FilemanagementException extends RuntimeException {
    public FilemanagementException(String message) {
        super(message);
    }
    public FilemanagementException(String message, Throwable cause) {
        super(message, cause);
    }
}
