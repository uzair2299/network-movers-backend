package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.PurchaseOrderItem;
import com.company.networkmovers.modules.asset.dto.request.PurchaseOrderItemRequest;
import com.company.networkmovers.modules.asset.dto.response.PurchaseOrderItemResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchaseOrderItemMapper implements GenericMapper<PurchaseOrderItem, PurchaseOrderItemRequest, PurchaseOrderItemResponse> {

    private final AssetMapper assetMapper;

    @Override
    public PurchaseOrderItem toEntity(PurchaseOrderItemRequest request) {
        if (request == null) return null;
        return PurchaseOrderItem.builder()
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .build();
    }

    @Override
    public PurchaseOrderItemResponse toResponse(PurchaseOrderItem entity) {
        if (entity == null) return null;
        return PurchaseOrderItemResponse.builder()
                .id(entity.getId())
                .asset(assetMapper.toResponse(entity.getAsset()))
                .quantity(entity.getQuantity())
                .unitPrice(entity.getUnitPrice())
                .totalPrice(entity.getTotalPrice())
                .build();
    }
}
