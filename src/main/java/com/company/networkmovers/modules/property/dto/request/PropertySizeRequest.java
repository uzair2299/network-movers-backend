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
public class PropertySizeRequest {
    private String name;
    private String code;
    
    @Builder.Default
    private boolean active = true;
    
    private UUID typeId;
    private String unitType;
}
