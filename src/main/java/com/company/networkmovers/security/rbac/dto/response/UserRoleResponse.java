package com.company.networkmovers.security.rbac.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

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
