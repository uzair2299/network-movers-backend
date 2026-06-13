package com.company.networkmovers.modules.asset.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetCategoryRequest {
    private String name;
    private String code;
    private UUID assetTypeId;
    private UUID parentCategoryId;
    private String description;
    @Builder.Default
    private boolean active = true;
}
