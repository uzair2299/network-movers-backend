package com.company.networkmovers.modules.rbac.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BulkRolePermissionRequest {
    private UUID roleId;
    private List<UUID> permissionIds;
}
