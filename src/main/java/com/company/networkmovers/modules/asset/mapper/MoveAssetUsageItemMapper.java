package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.MoveAssetUsageItem;
import com.company.networkmovers.modules.asset.dto.request.MoveAssetUsageItemRequest;
import com.company.networkmovers.modules.asset.dto.response.MoveAssetUsageItemResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoveAssetUsageItemMapper implements GenericMapper<MoveAssetUsageItem, MoveAssetUsageItemRequest, MoveAssetUsageItemResponse> {

    private final AssetMapper assetMapper;

    @Override
    public MoveAssetUsageItem toEntity(MoveAssetUsageItemRequest request) {
        if (request == null) return null;
        return MoveAssetUsageItem.builder()
                .quantity(request.getQuantity())
                .conditionOnIssue(request.getConditionOnIssue())
                .status(request.getStatus())
                .build();
    }

    @Override
    public MoveAssetUsageItemResponse toResponse(MoveAssetUsageItem entity) {
        if (entity == null) return null;
        return MoveAssetUsageItemResponse.builder()
                .id(entity.getId())
                .asset(assetMapper.toResponse(entity.getAsset()))
                .quantity(entity.getQuantity())
                .returnedQuantity(entity.getReturnedQuantity())
                .conditionOnIssue(entity.getConditionOnIssue())
                .conditionOnReturn(entity.getConditionOnReturn())
                .status(entity.getStatus())
                .build();
    }
}
