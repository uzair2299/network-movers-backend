package com.company.networkmovers.modules.asset.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetTransactionResponse {
    private UUID id;
    private AssetResponse asset;
    private String transactionType; // IN, OUT, TRANSFER, ADJUST, MAINTENANCE, DAMAGE
    private BigDecimal quantity;
    private AssetLocationResponse sourceLocation;
    private AssetLocationResponse destinationLocation;
    private String referenceType;
    private UUID referenceId;
    private String remarks;
    private LocalDateTime createdAt;
}
