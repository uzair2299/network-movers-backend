package com.company.networkmovers.modules.estimate.mapper;

import com.company.networkmovers.modules.estimate.entity.EstimateEntity;
import com.company.networkmovers.modules.estimate.dto.request.EstimateRequest;
import com.company.networkmovers.modules.estimate.dto.response.EstimateResponse;
import org.springframework.stereotype.Component;

@Component
public class EstimateMapper {

    public EstimateEntity toEntity(EstimateRequest request) {
        if (request == null) return null;
        return EstimateEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public EstimateResponse toResponse(EstimateEntity entity) {
        if (entity == null) return null;
        return EstimateResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
