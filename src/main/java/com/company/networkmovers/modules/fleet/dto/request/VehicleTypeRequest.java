package com.company.networkmovers.modules.fleet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypeRequest {
    private String name;
    private String code;
    @Builder.Default
    private boolean active = true;
    
    private String description;
}
