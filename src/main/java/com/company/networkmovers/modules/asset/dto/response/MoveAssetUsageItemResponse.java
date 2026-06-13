package com.company.networkmovers.modules.asset.dto.response;

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
public class MoveAssetUsageItemResponse {
    private UUID id;
    private AssetResponse asset;
    private BigDecimal quantity;
    private BigDecimal returnedQuantity;
    private String conditionOnIssue;
    private String conditionOnReturn;
    private String status; // ISSUED, RETURNED
}
