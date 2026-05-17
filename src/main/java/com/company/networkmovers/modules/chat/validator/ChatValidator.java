package com.company.networkmovers.modules.chat.validator;

import com.company.networkmovers.modules.chat.dto.request.ChatRequest;
import org.springframework.stereotype.Component;

@Component
public class ChatValidator {

    public void validate(ChatRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
