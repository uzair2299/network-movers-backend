package com.company.networkmovers.modules.property.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoveStatusTransitionResponse {
    private UUID id;
    private UUID fromStatusId;
    private UUID toStatusId;
    private String transitionName;
    private UUID allowedRoleId;
    private boolean requiresApproval;
    private boolean customerVisible;
    private boolean active;
}
