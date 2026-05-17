package com.company.networkmovers.modules.identity.mapper;

import com.company.networkmovers.modules.identity.entity.IdentityEntity;
import com.company.networkmovers.modules.identity.dto.request.IdentityRequest;
import com.company.networkmovers.modules.identity.dto.response.IdentityResponse;
import org.springframework.stereotype.Component;

@Component
public class IdentityMapper {

    public IdentityEntity toEntity(IdentityRequest request) {
        if (request == null) return null;
        return IdentityEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public IdentityResponse toResponse(IdentityEntity entity) {
        if (entity == null) return null;
        return IdentityResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
