package com.company.networkmovers.modules.rbac.service;

import com.company.networkmovers.modules.rbac.dto.request.BulkUserRoleRequest;
import com.company.networkmovers.modules.rbac.dto.request.UserRoleRequest;
import com.company.networkmovers.modules.rbac.dto.response.UserRoleResponse;
import com.company.networkmovers.shared.dto.RequestParamDto;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.UUID;

public interface UserRoleService {
    UserRoleResponse assignRole(UserRoleRequest request);
    List<UserRoleResponse> updateRolesBulk(BulkUserRoleRequest request);
    void unassignRole(UUID id);
    void unassignRolesBulk(List<UUID> ids);
    List<UserRoleResponse> getRolesForUser(UUID userId);
    List<UserRoleResponse> getUsersForRole(UUID roleId);
    Page<UserRoleResponse> getAll(RequestParamDto requestParams);
}
