package com.company.networkmovers.modules.property.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoveStatusTransitionRequest {
    private UUID id;
    private UUID fromStatusId;
    private UUID toStatusId;
    private String transitionName;
    private UUID allowedRoleId;
    private Boolean requiresApproval;
    private Boolean customerVisible;
    private Boolean active;
}
