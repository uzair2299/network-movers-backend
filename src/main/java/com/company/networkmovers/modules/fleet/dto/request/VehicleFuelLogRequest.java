package com.company.networkmovers.modules.fleet.dto.request;

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
public class VehicleFuelLogRequest {
    private UUID vehicleId;
    
    private LocalDateTime fuelDate;
    private BigDecimal fuelQuantityLiters;
    private BigDecimal costAmount;
    private BigDecimal odometerKm;
    
    private String fuelStation;
    private String remarks;
}
