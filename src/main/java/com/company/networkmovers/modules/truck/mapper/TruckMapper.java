package com.company.networkmovers.modules.truck.mapper;

import com.company.networkmovers.modules.truck.entity.TruckEntity;
import com.company.networkmovers.modules.truck.dto.request.TruckRequest;
import com.company.networkmovers.modules.truck.dto.response.TruckResponse;
import org.springframework.stereotype.Component;

@Component
public class TruckMapper {

    public TruckEntity toEntity(TruckRequest request) {
        if (request == null) return null;
        return TruckEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public TruckResponse toResponse(TruckEntity entity) {
        if (entity == null) return null;
        return TruckResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
