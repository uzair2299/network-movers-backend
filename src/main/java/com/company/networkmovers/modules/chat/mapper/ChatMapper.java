package com.company.networkmovers.modules.chat.mapper;

import com.company.networkmovers.modules.chat.entity.ChatEntity;
import com.company.networkmovers.modules.chat.dto.request.ChatRequest;
import com.company.networkmovers.modules.chat.dto.response.ChatResponse;
import org.springframework.stereotype.Component;

@Component
public class ChatMapper {

    public ChatEntity toEntity(ChatRequest request) {
        if (request == null) return null;
        return ChatEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public ChatResponse toResponse(ChatEntity entity) {
        if (entity == null) return null;
        return ChatResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
