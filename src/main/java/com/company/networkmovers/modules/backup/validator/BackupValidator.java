package com.company.networkmovers.modules.backup.validator;

import com.company.networkmovers.modules.backup.dto.request.BackupRequest;
import org.springframework.stereotype.Component;

@Component
public class BackupValidator {

    public void validate(BackupRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
