package com.company.networkmovers.modules.asset.service;

import com.company.networkmovers.modules.asset.dto.request.PurchaseOrderRequest;
import com.company.networkmovers.modules.asset.dto.response.PurchaseOrderResponse;
import com.company.networkmovers.shared.dto.RequestParamDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PurchaseOrderService {
    PurchaseOrderResponse create(PurchaseOrderRequest request);
    PurchaseOrderResponse update(UUID id, PurchaseOrderRequest request);
    PurchaseOrderResponse getById(UUID id);
    Page<PurchaseOrderResponse> getAll(RequestParamDto requestParams);
    PurchaseOrderResponse updateStatus(UUID id, String status, UUID receivingLocationId);
    void delete(UUID id);
}
