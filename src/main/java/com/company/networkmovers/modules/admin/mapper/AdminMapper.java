package com.company.networkmovers.modules.admin.mapper;

import com.company.networkmovers.modules.admin.entity.AdminEntity;
import com.company.networkmovers.modules.admin.dto.request.AdminRequest;
import com.company.networkmovers.modules.admin.dto.response.AdminResponse;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminEntity toEntity(AdminRequest request) {
        if (request == null) return null;
        return AdminEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public AdminResponse toResponse(AdminEntity entity) {
        if (entity == null) return null;
        return AdminResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
