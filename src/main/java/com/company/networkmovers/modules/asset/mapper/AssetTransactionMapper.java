package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.AssetTransaction;
import com.company.networkmovers.modules.asset.dto.response.AssetTransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssetTransactionMapper {

    private final AssetMapper assetMapper;
    private final AssetLocationMapper assetLocationMapper;

    public AssetTransactionResponse toResponse(AssetTransaction entity) {
        if (entity == null) return null;
        return AssetTransactionResponse.builder()
                .id(entity.getId())
                .asset(assetMapper.toResponse(entity.getAsset()))
                .transactionType(entity.getTransactionType())
                .quantity(entity.getQuantity())
                .sourceLocation(assetLocationMapper.toResponse(entity.getSourceLocation()))
                .destinationLocation(assetLocationMapper.toResponse(entity.getDestinationLocation()))
                .referenceType(entity.getReferenceType())
                .referenceId(entity.getReferenceId())
                .remarks(entity.getRemarks())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
