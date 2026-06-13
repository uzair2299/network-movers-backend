package com.company.networkmovers.modules.asset.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoveAssetUsageRequest {
    private UUID bookingId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String remarks;
    private String status; // ISSUED, RETURNED, PARTIALLY_RETURNED
    private List<MoveAssetUsageItemRequest> items;
}
