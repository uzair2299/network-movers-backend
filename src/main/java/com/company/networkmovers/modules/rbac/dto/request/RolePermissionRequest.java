package com.company.networkmovers.modules.rbac.dto.request;

import lombok.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionRequest {
    private UUID roleId;
    private UUID permissionId;
    @Builder.Default
    private boolean active = true;
}
