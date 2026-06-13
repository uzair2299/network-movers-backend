package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.StockAuditItem;
import com.company.networkmovers.modules.asset.dto.request.StockAuditItemRequest;
import com.company.networkmovers.modules.asset.dto.response.StockAuditItemResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockAuditItemMapper implements GenericMapper<StockAuditItem, StockAuditItemRequest, StockAuditItemResponse> {

    private final AssetMapper assetMapper;
    private final AssetLocationMapper locationMapper;

    @Override
    public StockAuditItem toEntity(StockAuditItemRequest request) {
        if (request == null) return null;
        return StockAuditItem.builder()
                .expectedQuantity(request.getExpectedQuantity())
                .actualQuantity(request.getActualQuantity())
                .remarks(request.getRemarks())
                .build();
    }

    @Override
    public StockAuditItemResponse toResponse(StockAuditItem entity) {
        if (entity == null) return null;
        return StockAuditItemResponse.builder()
                .id(entity.getId())
                .asset(assetMapper.toResponse(entity.getAsset()))
                .location(locationMapper.toResponse(entity.getLocation()))
                .expectedQuantity(entity.getExpectedQuantity())
                .actualQuantity(entity.getActualQuantity())
                .discrepancy(entity.getDiscrepancy())
                .remarks(entity.getRemarks())
                .build();
    }
}
