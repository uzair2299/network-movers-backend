package com.company.networkmovers.modules.asset.dto.request;

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
public class DamageReportRequest {
    private UUID assetId;
    private UUID reportedBy;
    private LocalDate damageDate;
    private String description;
    private String severity; // LOW, MEDIUM, HIGH, CRITICAL
    private String status; // REPORTED, UNDER_REVIEW, REPAIRED, SCRAPPED
    private String actionTaken;
}
