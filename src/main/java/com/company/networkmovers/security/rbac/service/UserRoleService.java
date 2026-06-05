package com.company.networkmovers.security.rbac.service;

import com.company.networkmovers.security.rbac.dto.request.BulkUserRoleRequest;
import com.company.networkmovers.security.rbac.dto.request.UserRoleRequest;
import com.company.networkmovers.security.rbac.dto.response.UserRoleResponse;

import java.util.List;
import java.util.UUID;

public interface UserRoleService {
    UserRoleResponse assignRole(UserRoleRequest request);
    List<UserRoleResponse> assignRolesBulk(BulkUserRoleRequest request);
    
    void unassignRole(UUID id);
    void unassignRolesBulk(List<UUID> ids);
    
    List<UserRoleResponse> getRolesForUser(UUID userId);
    List<UserRoleResponse> getUsersForRole(UUID roleId);
    org.springframework.data.domain.Page<UserRoleResponse> getAll(com.company.networkmovers.shared.dto.RequestParamDto requestParams);
}
