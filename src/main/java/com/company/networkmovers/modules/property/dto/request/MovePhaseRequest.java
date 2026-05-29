package com.company.networkmovers.modules.property.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovePhaseRequest {
    private String name;
    private String code;
    @Builder.Default
    private boolean active = true;
    private Integer sequenceNo;
}
