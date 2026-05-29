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
public class MoveStatusRequest {
    private String name;
    private String code;
    @Builder.Default
    private boolean active = true;
    private UUID phaseId;
    private String description;
    private Integer sequenceNo;
    @Builder.Default
    private boolean isFinal = false;
    private String colorCode;
    @Builder.Default
    private boolean customerVisible = true;
    @Builder.Default
    private boolean internalOnly = false;
}
