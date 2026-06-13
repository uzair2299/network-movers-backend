package com.company.networkmovers.modules.asset.dto.response;

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
public class AssetMaintenanceResponse {
    private UUID id;
    private AssetResponse asset;
    private String maintenanceType; // PREVENTIVE, REPAIR, CALIBRATION
    private String description;
    private LocalDate scheduledDate;
    private LocalDate startDate;
    private LocalDate completionDate;
    private BigDecimal cost;
    private String performedBy;
    private String remarks;
    private String status; // SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
