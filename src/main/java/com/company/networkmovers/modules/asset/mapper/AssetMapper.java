package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.Asset;
import com.company.networkmovers.modules.asset.dto.request.AssetRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class AssetMapper implements GenericMapper<Asset, AssetRequest, AssetResponse> {

    private final AssetTypeMapper assetTypeMapper;
    private final AssetCategoryMapper assetCategoryMapper;
    private final AssetCompanyMapper assetCompanyMapper;
    private final UnitOfMeasureMapper unitOfMeasureMapper;
    private final AssetSupplierMapper assetSupplierMapper;

    public AssetMapper(AssetTypeMapper assetTypeMapper, AssetCategoryMapper assetCategoryMapper,
                       AssetCompanyMapper assetCompanyMapper, UnitOfMeasureMapper unitOfMeasureMapper,
                       AssetSupplierMapper assetSupplierMapper) {
        this.assetTypeMapper = assetTypeMapper;
        this.assetCategoryMapper = assetCategoryMapper;
        this.assetCompanyMapper = assetCompanyMapper;
        this.unitOfMeasureMapper = unitOfMeasureMapper;
        this.assetSupplierMapper = assetSupplierMapper;
    }

    @Override
    public Asset toEntity(AssetRequest request) {
        if (request == null) return null;
        return Asset.builder()
                .code(request.getCode())
                .name(request.getName())
                .sku(request.getSku())
                .barcode(request.getBarcode())
                .description(request.getDescription())
                .model(request.getModel())
                .serialNumber(request.getSerialNumber())
                .purchaseDate(request.getPurchaseDate())
                .purchaseCost(request.getPurchaseCost())
                .status(request.getStatus())
                .active(request.isActive())
                .build();
    }

    @Override
    public AssetResponse toResponse(Asset entity) {
        if (entity == null) return null;
        return AssetResponse.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .sku(entity.getSku())
                .barcode(entity.getBarcode())
                .description(entity.getDescription())
                .model(entity.getModel())
                .serialNumber(entity.getSerialNumber())
                .assetType(assetTypeMapper.toResponse(entity.getAssetType()))
                .category(assetCategoryMapper.toResponse(entity.getCategory()))
                .company(assetCompanyMapper.toResponse(entity.getCompany()))
                .unitOfMeasure(unitOfMeasureMapper.toResponse(entity.getUnitOfMeasure()))
                .supplier(assetSupplierMapper.toResponse(entity.getSupplier()))
                .purchaseDate(entity.getPurchaseDate())
                .purchaseCost(entity.getPurchaseCost())
                .status(entity.getStatus())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
