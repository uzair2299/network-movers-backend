package com.company.networkmovers.modules.warehouse.mapper;

import com.company.networkmovers.modules.warehouse.entity.WarehouseEntity;
import com.company.networkmovers.modules.warehouse.dto.request.WarehouseRequest;
import com.company.networkmovers.modules.warehouse.dto.response.WarehouseResponse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {

    public WarehouseEntity toEntity(WarehouseRequest request) {
        if (request == null) return null;
        return WarehouseEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public WarehouseResponse toResponse(WarehouseEntity entity) {
        if (entity == null) return null;
        return WarehouseResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
