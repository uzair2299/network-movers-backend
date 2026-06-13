package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.PurchaseOrder;
import com.company.networkmovers.modules.asset.dto.request.PurchaseOrderRequest;
import com.company.networkmovers.modules.asset.dto.response.PurchaseOrderResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PurchaseOrderMapper implements GenericMapper<PurchaseOrder, PurchaseOrderRequest, PurchaseOrderResponse> {

    private final AssetSupplierMapper supplierMapper;
    private final PurchaseOrderItemMapper itemMapper;

    @Override
    public PurchaseOrder toEntity(PurchaseOrderRequest request) {
        if (request == null) return null;
        return PurchaseOrder.builder()
                .purchaseOrderNumber(request.getPurchaseOrderNumber())
                .orderDate(request.getOrderDate())
                .expectedDeliveryDate(request.getExpectedDeliveryDate())
                .status(request.getStatus())
                .build();
    }

    @Override
    public PurchaseOrderResponse toResponse(PurchaseOrder entity) {
        if (entity == null) return null;
        return PurchaseOrderResponse.builder()
                .id(entity.getId())
                .purchaseOrderNumber(entity.getPurchaseOrderNumber())
                .supplier(supplierMapper.toResponse(entity.getSupplier()))
                .orderDate(entity.getOrderDate())
                .expectedDeliveryDate(entity.getExpectedDeliveryDate())
                .totalAmount(entity.getTotalAmount())
                .status(entity.getStatus())
                .items(entity.getItems() == null ? new ArrayList<>() : entity.getItems().stream()
                        .map(itemMapper::toResponse)
                        .collect(Collectors.toList()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
