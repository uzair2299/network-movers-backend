package com.company.networkmovers.security.rbac.mapper;

import com.company.networkmovers.security.rbac.Role;
import com.company.networkmovers.security.rbac.dto.request.RoleRequest;
import com.company.networkmovers.security.rbac.dto.response.RoleResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements GenericMapper<Role, RoleRequest, RoleResponse> {

    public Role toEntity(RoleRequest request) {
        if (request == null) return null;
        
        return Role.builder()
                .name(request.getName())
                .code(request.getCode())
                .description(request.getDescription())
                .active(request.isActive())
                .build();
    }

    public RoleResponse toResponse(Role role) {
        if (role == null) return null;
        
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .code(role.getCode())
                .description(role.getDescription())
                .active(role.isActive())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .build();
    }

    public void updateEntity(Role role, RoleRequest request) {
        if (request == null) return;
        
        if (request.getName() != null) {
            role.setName(request.getName());
        }
        if (request.getCode() != null) {
            role.setCode(request.getCode());
        }
        if (request.getDescription() != null) {
            role.setDescription(request.getDescription());
        }
        role.setActive(request.isActive());
    }
}
