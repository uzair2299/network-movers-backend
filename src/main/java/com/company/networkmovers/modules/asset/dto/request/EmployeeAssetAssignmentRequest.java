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
public class EmployeeAssetAssignmentRequest {
    private UUID employeeId;
    private UUID assetId;
    private LocalDate assignedDate;
    private LocalDate returnedDate;
    private String conditionOnIssue;
    private String conditionOnReturn;
    private String remarks;
    private String status; // ASSIGNED, RETURNED
}
