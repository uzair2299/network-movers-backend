package com.company.networkmovers.modules.warehouse.facade;

import java.util.UUID;

import com.company.networkmovers.modules.warehouse.dto.request.WarehouseRequest;
import com.company.networkmovers.modules.warehouse.dto.response.WarehouseResponse;
import com.company.networkmovers.modules.warehouse.service.WarehouseService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class WarehouseFacade {

    private final WarehouseService service;

    public WarehouseFacade(WarehouseService service) {
        this.service = service;
    }

    public WarehouseResponse create(WarehouseRequest request) {
        return service.create(request);
    }

    public WarehouseResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<WarehouseResponse> findAll() {
        return service.findAll();
    }

    public WarehouseResponse update(UUID id, WarehouseRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
