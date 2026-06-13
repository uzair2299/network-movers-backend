package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.UnitOfMeasure;
import com.company.networkmovers.modules.asset.dto.request.UnitOfMeasureRequest;
import com.company.networkmovers.modules.asset.dto.response.UnitOfMeasureResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureMapper implements GenericMapper<UnitOfMeasure, UnitOfMeasureRequest, UnitOfMeasureResponse> {

    @Override
    public UnitOfMeasure toEntity(UnitOfMeasureRequest request) {
        if (request == null) return null;
        return UnitOfMeasure.builder()
                .name(request.getName())
                .code(request.getCode())
                .active(request.isActive())
                .build();
    }

    @Override
    public UnitOfMeasureResponse toResponse(UnitOfMeasure entity) {
        if (entity == null) return null;
        return UnitOfMeasureResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
