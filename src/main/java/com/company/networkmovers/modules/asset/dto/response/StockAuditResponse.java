package com.company.networkmovers.modules.asset.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockAuditResponse {
    private UUID id;
    private LocalDate auditDate;
    private UUID auditorId;
    private String remarks;
    private String status; // DRAFT, IN_PROGRESS, COMPLETED, CANCELLED
    private List<StockAuditItemResponse> items;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
