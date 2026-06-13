package com.company.networkmovers.modules.asset.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetRequest {
    private String code;
    private String name;
    private String sku;
    private String barcode;
    private String description;
    private String model;
    private String serialNumber;
    private UUID assetTypeId;
    private UUID categoryId;
    private UUID companyId;
    private UUID unitOfMeasureId;
    private UUID supplierId;
    private LocalDate purchaseDate;
    private BigDecimal purchaseCost;
    private String status; // ACTIVE, MAINTENANCE, RETIRED, LOST, DAMAGED
    @Builder.Default
    private boolean active = true;
}
