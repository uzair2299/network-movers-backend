package com.company.networkmovers.security.rbac.dto.request;

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
public class BulkUserRoleRequest {
    private Long userId;
    private List<UUID> roleIds;
}
