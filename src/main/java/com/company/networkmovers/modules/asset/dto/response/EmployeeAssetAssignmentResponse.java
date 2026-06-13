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
public class EmployeeAssetAssignmentResponse {
    private UUID id;
    private UUID employeeId;
    private AssetResponse asset;
    private LocalDate assignedDate;
    private LocalDate returnedDate;
    private String conditionOnIssue;
    private String conditionOnReturn;
    private String remarks;
    private String status; // ASSIGNED, RETURNED
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
