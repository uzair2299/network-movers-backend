package com.company.networkmovers.modules.inventory.service;

import com.company.networkmovers.modules.inventory.dto.request.InventoryRequest;
import com.company.networkmovers.modules.inventory.dto.response.InventoryResponse;
import java.util.List;

public interface InventoryService {
    InventoryResponse create(InventoryRequest request);
    InventoryResponse findById(Long id);
    List<InventoryResponse> findAll();
    InventoryResponse update(Long id, InventoryRequest request);
    void delete(Long id);
}
