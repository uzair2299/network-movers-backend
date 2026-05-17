package com.company.networkmovers.modules.ai.mapper;

import com.company.networkmovers.modules.ai.entity.AiEntity;
import com.company.networkmovers.modules.ai.dto.request.AiRequest;
import com.company.networkmovers.modules.ai.dto.response.AiResponse;
import org.springframework.stereotype.Component;

@Component
public class AiMapper {

    public AiEntity toEntity(AiRequest request) {
        if (request == null) return null;
        return AiEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public AiResponse toResponse(AiEntity entity) {
        if (entity == null) return null;
        return AiResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
