package com.company.networkmovers.modules.warehouse.service;

import java.util.UUID;

import com.company.networkmovers.modules.warehouse.dto.request.WarehouseRequest;
import com.company.networkmovers.modules.warehouse.dto.response.WarehouseResponse;
import java.util.List;

public interface WarehouseService {
    WarehouseResponse create(WarehouseRequest request);
    WarehouseResponse findById(UUID id);
    List<WarehouseResponse> findAll();
    WarehouseResponse update(UUID id, WarehouseRequest request);
    void delete(UUID id);
}
