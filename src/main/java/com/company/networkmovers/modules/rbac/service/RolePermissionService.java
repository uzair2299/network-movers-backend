package com.company.networkmovers.modules.rbac.service;

import com.company.networkmovers.modules.rbac.dto.request.BulkRolePermissionRequest;
import com.company.networkmovers.modules.rbac.dto.request.RolePermissionRequest;
import com.company.networkmovers.modules.rbac.dto.response.RolePermissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface RolePermissionService {
    RolePermissionResponse assign(RolePermissionRequest request);
    List<RolePermissionResponse> assignBulk(BulkRolePermissionRequest request);
    void revoke(UUID id);
    RolePermissionResponse getById(UUID id);
    Page<RolePermissionResponse> getAll(Pageable pageable);
    List<RolePermissionResponse> getByRoleId(UUID roleId);
    List<RolePermissionResponse> getByPermissionId(UUID permissionId);
}
