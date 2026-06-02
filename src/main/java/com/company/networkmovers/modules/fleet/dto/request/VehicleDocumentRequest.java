package com.company.networkmovers.modules.fleet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDocumentRequest {
    private UUID vehicleId;
    private UUID documentTypeId;
    
    private String documentNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    
    private String fileUrl;
    
    @Builder.Default
    private boolean verified = false;
    
    private String remarks;
}
