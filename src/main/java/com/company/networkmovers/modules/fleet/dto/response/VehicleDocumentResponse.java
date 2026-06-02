package com.company.networkmovers.modules.fleet.dto.response;

import com.company.networkmovers.modules.document.dto.response.DocumentTypeResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDocumentResponse {
    private UUID id;
    
    private UUID vehicleId;
    private DocumentTypeResponse documentType;
    
    private String documentNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    
    private String fileUrl;
    private boolean verified;
    private String remarks;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
