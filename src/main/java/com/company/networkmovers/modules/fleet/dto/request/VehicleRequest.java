package com.company.networkmovers.modules.fleet.dto.request;

import com.company.networkmovers.modules.fleet.entity.enums.OwnershipType;
import com.company.networkmovers.modules.fleet.entity.enums.VehicleStatus;
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
public class VehicleRequest {
    private String vehicleCode;
    private String registrationNo;
    private UUID vehicleModelId;
    
    private Integer manufactureYear;
    private OwnershipType ownershipType;
    private VehicleStatus status;
    
    private BigDecimal currentOdometerKm;
    private LocalDate insuranceExpiryDate;
    private LocalDate fitnessExpiryDate;
    private LocalDate acquisitionDate;
    
    @Builder.Default
    private boolean active = true;
    
    private String remarks;
}
