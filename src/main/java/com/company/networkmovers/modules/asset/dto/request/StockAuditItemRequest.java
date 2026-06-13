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
public class StockAuditItemRequest {
    private UUID assetId;
    private UUID locationId;
    private BigDecimal expectedQuantity;
    private BigDecimal actualQuantity;
    private String remarks;
}
