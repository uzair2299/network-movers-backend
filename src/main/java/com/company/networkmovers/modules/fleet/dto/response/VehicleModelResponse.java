package com.company.networkmovers.modules.fleet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleModelResponse {
    private UUID id;
    
    private VehicleMakeResponse make;
    private VehicleTypeResponse vehicleType;

    private String code;
    private String name;
    private boolean active;

    private BigDecimal capacityKg;
    private BigDecimal capacityM3;
    private BigDecimal lengthM;
    private BigDecimal widthM;
    private BigDecimal heightM;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
