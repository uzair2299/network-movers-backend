package com.company.networkmovers.modules.fleet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMaintenanceResponse {
    private UUID id;
    
    private UUID vehicleId;
    private VehicleMaintenanceTypeResponse maintenanceType;
    
    private LocalDate maintenanceDate;
    private BigDecimal odometerKm;
    private BigDecimal cost;
    
    private String vendorName;
    
    private LocalDate nextServiceDate;
    private BigDecimal nextServiceKm;
    
    private String remarks;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
