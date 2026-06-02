package com.company.networkmovers.modules.fleet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMaintenanceRequest {
    private UUID vehicleId;
    private UUID maintenanceTypeId;
    
    private LocalDate maintenanceDate;
    private BigDecimal odometerKm;
    private BigDecimal cost;
    
    private String vendorName;
    
    private LocalDate nextServiceDate;
    private BigDecimal nextServiceKm;
    
    private String remarks;
}
