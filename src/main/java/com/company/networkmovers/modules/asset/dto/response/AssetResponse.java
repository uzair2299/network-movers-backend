package com.company.networkmovers.modules.asset.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetResponse {
    private UUID id;
    private String code;
    private String name;
    private String sku;
    private String barcode;
    private String description;
    private String model;
    private String serialNumber;
    private AssetTypeResponse assetType;
    private AssetCategoryResponse category;
    private AssetCompanyResponse company;
    private UnitOfMeasureResponse unitOfMeasure;
    private AssetSupplierResponse supplier;
    private LocalDate purchaseDate;
    private BigDecimal purchaseCost;
    private String status;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
