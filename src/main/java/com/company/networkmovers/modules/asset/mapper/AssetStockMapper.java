package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.AssetStock;
import com.company.networkmovers.modules.asset.dto.request.AssetStockRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetStockResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssetStockMapper implements GenericMapper<AssetStock, AssetStockRequest, AssetStockResponse> {

    private final AssetMapper assetMapper;
    private final AssetLocationMapper assetLocationMapper;

    @Override
    public AssetStock toEntity(AssetStockRequest request) {
        if (request == null) return null;
        return AssetStock.builder()
                .quantity(request.getQuantity())
                .minimumQuantity(request.getMinimumQuantity())
                .maximumQuantity(request.getMaximumQuantity())
                .build();
    }

    @Override
    public AssetStockResponse toResponse(AssetStock entity) {
        if (entity == null) return null;
        return AssetStockResponse.builder()
                .id(entity.getId())
                .asset(assetMapper.toResponse(entity.getAsset()))
                .location(assetLocationMapper.toResponse(entity.getLocation()))
                .quantity(entity.getQuantity())
                .minimumQuantity(entity.getMinimumQuantity())
                .maximumQuantity(entity.getMaximumQuantity())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
