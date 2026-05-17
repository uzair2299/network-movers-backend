package com.company.networkmovers.modules.claims.mapper;

import com.company.networkmovers.modules.claims.entity.ClaimsEntity;
import com.company.networkmovers.modules.claims.dto.request.ClaimsRequest;
import com.company.networkmovers.modules.claims.dto.response.ClaimsResponse;
import org.springframework.stereotype.Component;

@Component
public class ClaimsMapper {

    public ClaimsEntity toEntity(ClaimsRequest request) {
        if (request == null) return null;
        return ClaimsEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public ClaimsResponse toResponse(ClaimsEntity entity) {
        if (entity == null) return null;
        return ClaimsResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
