package com.company.networkmovers.modules.asset.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoveAssetUsageItemRequest {
    private UUID assetId;
    private BigDecimal quantity;
    private String conditionOnIssue;
    private String status; // ISSUED, RETURNED
}
