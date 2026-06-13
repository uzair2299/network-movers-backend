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
public class StockAuditItemResponse {
    private UUID id;
    private AssetResponse asset;
    private AssetLocationResponse location;
    private BigDecimal expectedQuantity;
    private BigDecimal actualQuantity;
    private BigDecimal discrepancy;
    private String remarks;
}
