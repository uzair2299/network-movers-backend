package com.company.networkmovers.modules.fleet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleModelRequest {
    private UUID makeId;
    private UUID vehicleTypeId;

    private String code;
    private String name;
    
    @Builder.Default
    private boolean active = true;

    private BigDecimal capacityKg;
    private BigDecimal capacityM3;
    private BigDecimal lengthM;
    private BigDecimal widthM;
    private BigDecimal heightM;
}
