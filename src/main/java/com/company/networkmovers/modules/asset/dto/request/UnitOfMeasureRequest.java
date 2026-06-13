package com.company.networkmovers.modules.asset.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitOfMeasureRequest {
    private String name;
    private String code;
    @Builder.Default
    private boolean active = true;
}
