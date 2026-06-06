package com.company.networkmovers.modules.rbac.dto.request;

import lombok.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRequest {
    private UUID userId;
    private UUID roleId;
}
