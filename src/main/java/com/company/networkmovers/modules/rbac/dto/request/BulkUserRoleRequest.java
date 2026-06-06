package com.company.networkmovers.modules.rbac.dto.request;

import lombok.*;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BulkUserRoleRequest {
    private UUID userId;
    private List<UUID> roleIds;
}
