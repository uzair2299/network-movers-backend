package com.company.networkmovers.modules.settings.exception;

public class SettingsException extends RuntimeException {
    public SettingsException(String message) {
        super(message);
    }
    public SettingsException(String message, Throwable cause) {
        super(message, cause);
    }
}
