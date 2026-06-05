package com.company.networkmovers.modules.inventory.service;

import java.util.UUID;

import com.company.networkmovers.modules.inventory.dto.request.InventoryRequest;
import com.company.networkmovers.modules.inventory.dto.response.InventoryResponse;
import java.util.List;

public interface InventoryService {
    InventoryResponse create(InventoryRequest request);
    InventoryResponse findById(UUID id);
    List<InventoryResponse> findAll();
    InventoryResponse update(UUID id, InventoryRequest request);
    void delete(UUID id);
}
