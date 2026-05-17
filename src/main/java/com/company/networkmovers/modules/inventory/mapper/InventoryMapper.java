package com.company.networkmovers.modules.inventory.mapper;

import com.company.networkmovers.modules.inventory.entity.InventoryEntity;
import com.company.networkmovers.modules.inventory.dto.request.InventoryRequest;
import com.company.networkmovers.modules.inventory.dto.response.InventoryResponse;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public InventoryEntity toEntity(InventoryRequest request) {
        if (request == null) return null;
        return InventoryEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public InventoryResponse toResponse(InventoryEntity entity) {
        if (entity == null) return null;
        return InventoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
