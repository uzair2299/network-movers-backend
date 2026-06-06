package com.company.networkmovers.modules.rbac.mapper;

import com.company.networkmovers.modules.rbac.entity.Role;
import com.company.networkmovers.modules.rbac.dto.request.RoleRequest;
import com.company.networkmovers.modules.rbac.dto.response.RoleResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component("modulesRoleMapper")
public class RoleMapper implements GenericMapper<Role, RoleRequest, RoleResponse> {
    @Override
    public Role toEntity(RoleRequest request) {
        if (request == null) return null;
        return Role.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .active(request.isActive())
                .build();
    }

    @Override
    public RoleResponse toResponse(Role entity) {
        if (entity == null) return null;
        return RoleResponse.builder()
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
