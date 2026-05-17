package com.company.networkmovers.modules.warehouse.service;

import com.company.networkmovers.modules.warehouse.dto.request.WarehouseRequest;
import com.company.networkmovers.modules.warehouse.dto.response.WarehouseResponse;
import java.util.List;

public interface WarehouseService {
    WarehouseResponse create(WarehouseRequest request);
    WarehouseResponse findById(Long id);
    List<WarehouseResponse> findAll();
    WarehouseResponse update(Long id, WarehouseRequest request);
    void delete(Long id);
}
