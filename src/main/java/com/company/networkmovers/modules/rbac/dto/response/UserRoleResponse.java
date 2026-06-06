package com.company.networkmovers.modules.rbac.dto.response;

import lombok.*;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleResponse {
    private UUID id;
    private UUID userId;
    private RoleResponse role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
