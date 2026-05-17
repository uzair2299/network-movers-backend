package com.company.networkmovers.modules.dispatch.mapper;

import com.company.networkmovers.modules.dispatch.entity.DispatchEntity;
import com.company.networkmovers.modules.dispatch.dto.request.DispatchRequest;
import com.company.networkmovers.modules.dispatch.dto.response.DispatchResponse;
import org.springframework.stereotype.Component;

@Component
public class DispatchMapper {

    public DispatchEntity toEntity(DispatchRequest request) {
        if (request == null) return null;
        return DispatchEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public DispatchResponse toResponse(DispatchEntity entity) {
        if (entity == null) return null;
        return DispatchResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
