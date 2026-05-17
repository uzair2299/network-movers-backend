package com.company.networkmovers.modules.filemanagement.validator;

import com.company.networkmovers.modules.filemanagement.dto.request.FilemanagementRequest;
import org.springframework.stereotype.Component;

@Component
public class FilemanagementValidator {

    public void validate(FilemanagementRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
