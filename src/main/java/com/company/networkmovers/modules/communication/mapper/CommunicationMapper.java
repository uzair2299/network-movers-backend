package com.company.networkmovers.modules.communication.mapper;

import com.company.networkmovers.modules.communication.entity.CommunicationEntity;
import com.company.networkmovers.modules.communication.dto.request.CommunicationRequest;
import com.company.networkmovers.modules.communication.dto.response.CommunicationResponse;
import org.springframework.stereotype.Component;

@Component
public class CommunicationMapper {

    public CommunicationEntity toEntity(CommunicationRequest request) {
        if (request == null) return null;
        return CommunicationEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public CommunicationResponse toResponse(CommunicationEntity entity) {
        if (entity == null) return null;
        return CommunicationResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
