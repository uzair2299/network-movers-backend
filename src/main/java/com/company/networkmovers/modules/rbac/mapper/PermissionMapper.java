package com.company.networkmovers.modules.rbac.mapper;

import com.company.networkmovers.modules.rbac.entity.Permission;
import com.company.networkmovers.modules.rbac.dto.request.PermissionRequest;
import com.company.networkmovers.modules.rbac.dto.response.PermissionResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component("modulesPermissionMapper")
public class PermissionMapper implements GenericMapper<Permission, PermissionRequest, PermissionResponse> {

    @Override
    public Permission toEntity(PermissionRequest request) {
        if (request == null) return null;
        return Permission.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .active(request.isActive())
                .build();
    }

    @Override
    public PermissionResponse toResponse(Permission entity) {
        if (entity == null) return null;
        return PermissionResponse.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
