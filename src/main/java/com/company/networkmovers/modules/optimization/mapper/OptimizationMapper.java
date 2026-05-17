package com.company.networkmovers.modules.optimization.mapper;

import com.company.networkmovers.modules.optimization.entity.OptimizationEntity;
import com.company.networkmovers.modules.optimization.dto.request.OptimizationRequest;
import com.company.networkmovers.modules.optimization.dto.response.OptimizationResponse;
import org.springframework.stereotype.Component;

@Component
public class OptimizationMapper {

    public OptimizationEntity toEntity(OptimizationRequest request) {
        if (request == null) return null;
        return OptimizationEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public OptimizationResponse toResponse(OptimizationEntity entity) {
        if (entity == null) return null;
        return OptimizationResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
