package com.company.networkmovers.modules.rbac.mapper;

import com.company.networkmovers.modules.rbac.entity.UserRole;
import com.company.networkmovers.modules.rbac.dto.request.UserRoleRequest;
import com.company.networkmovers.modules.rbac.dto.response.UserRoleResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("modulesUserRoleMapper")
public class UserRoleMapper implements GenericMapper<UserRole, UserRoleRequest, UserRoleResponse> {

    private final RoleMapper roleMapper;

    public UserRoleMapper(@Qualifier("modulesRoleMapper") RoleMapper roleMapper) {
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
