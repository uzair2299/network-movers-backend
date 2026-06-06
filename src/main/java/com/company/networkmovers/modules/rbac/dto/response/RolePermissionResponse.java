package com.company.networkmovers.modules.rbac.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionResponse {
    private UUID id;
    private UUID roleId;
    private String roleName;
    private String roleCode;
    private UUID permissionId;
    private String permissionName;
    private String permissionCode;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
