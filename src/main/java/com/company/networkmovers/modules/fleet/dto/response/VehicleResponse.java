package com.company.networkmovers.modules.fleet.dto.response;

import com.company.networkmovers.modules.fleet.entity.enums.OwnershipType;
import com.company.networkmovers.modules.fleet.entity.enums.VehicleStatus;
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
public class VehicleResponse {
    private UUID id;
    
    private String vehicleCode;
    private String registrationNo;
    
    private VehicleModelResponse vehicleModel;
    
    private Integer manufactureYear;
    private OwnershipType ownershipType;
    private VehicleStatus status;
    
    private BigDecimal currentOdometerKm;
    private LocalDate insuranceExpiryDate;
    private LocalDate fitnessExpiryDate;
    private LocalDate acquisitionDate;
    
    private boolean active;
    private String remarks;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
