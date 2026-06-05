package com.company.networkmovers.modules.inventory.facade;

import java.util.UUID;

import com.company.networkmovers.modules.inventory.dto.request.InventoryRequest;
import com.company.networkmovers.modules.inventory.dto.response.InventoryResponse;
import com.company.networkmovers.modules.inventory.service.InventoryService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class InventoryFacade {

    private final InventoryService service;

    public InventoryFacade(InventoryService service) {
        this.service = service;
    }

    public InventoryResponse create(InventoryRequest request) {
        return service.create(request);
    }

    public InventoryResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<InventoryResponse> findAll() {
        return service.findAll();
    }

    public InventoryResponse update(UUID id, InventoryRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
