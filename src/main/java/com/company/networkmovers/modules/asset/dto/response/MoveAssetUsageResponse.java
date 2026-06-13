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
public class MoveAssetUsageResponse {
    private UUID id;
    private UUID bookingId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String remarks;
    private String status; // ISSUED, RETURNED, PARTIALLY_RETURNED
    private List<MoveAssetUsageItemResponse> items;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
