package com.company.networkmovers.modules.truck.service;

import java.util.UUID;

import com.company.networkmovers.modules.truck.dto.request.TruckRequest;
import com.company.networkmovers.modules.truck.dto.response.TruckResponse;
import java.util.List;

public interface TruckService {
    TruckResponse create(TruckRequest request);
    TruckResponse findById(UUID id);
    List<TruckResponse> findAll();
    TruckResponse update(UUID id, TruckRequest request);
    void delete(UUID id);
}
