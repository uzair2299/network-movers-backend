package com.company.networkmovers.modules.support.mapper;

import com.company.networkmovers.modules.support.entity.SupportEntity;
import com.company.networkmovers.modules.support.dto.request.SupportRequest;
import com.company.networkmovers.modules.support.dto.response.SupportResponse;
import org.springframework.stereotype.Component;

@Component
public class SupportMapper {

    public SupportEntity toEntity(SupportRequest request) {
        if (request == null) return null;
        return SupportEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public SupportResponse toResponse(SupportEntity entity) {
        if (entity == null) return null;
        return SupportResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
