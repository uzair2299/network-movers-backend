package com.company.networkmovers.modules.audit.mapper;

import com.company.networkmovers.modules.audit.entity.AuditEntity;
import com.company.networkmovers.modules.audit.dto.request.AuditRequest;
import com.company.networkmovers.modules.audit.dto.response.AuditResponse;
import org.springframework.stereotype.Component;

@Component
public class AuditMapper {

    public AuditEntity toEntity(AuditRequest request) {
        if (request == null) return null;
        return AuditEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public AuditResponse toResponse(AuditEntity entity) {
        if (entity == null) return null;
        return AuditResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
