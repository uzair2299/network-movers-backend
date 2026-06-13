package com.company.networkmovers.modules.asset.dto.response;

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
public class DamageReportResponse {
    private UUID id;
    private AssetResponse asset;
    private UUID reportedBy;
    private LocalDate damageDate;
    private String description;
    private String severity; // LOW, MEDIUM, HIGH, CRITICAL
    private String status; // REPORTED, UNDER_REVIEW, REPAIRED, SCRAPPED
    private String actionTaken;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
