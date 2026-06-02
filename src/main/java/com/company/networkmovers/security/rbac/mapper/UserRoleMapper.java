package com.company.networkmovers.security.rbac.mapper;

import com.company.networkmovers.security.rbac.UserRole;
import com.company.networkmovers.security.rbac.dto.request.UserRoleRequest;
import com.company.networkmovers.security.rbac.dto.response.UserRoleResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper implements GenericMapper<UserRole, UserRoleRequest, UserRoleResponse> {

    private final RoleMapper roleMapper;

    public UserRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public UserRole toEntity(UserRoleRequest request) {
        if (request == null) return null;
        
        return UserRole.builder()
                .userId(request.getUserId())
                // Role reference must be set by the service using roleRepository
                .build();
    }

    @Override
    public UserRoleResponse toResponse(UserRole entity) {
        if (entity == null) return null;
        
        return UserRoleResponse.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .role(roleMapper.toResponse(entity.getRole()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
