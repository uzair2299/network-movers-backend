package com.company.networkmovers.modules.truck.service;

import com.company.networkmovers.modules.truck.dto.request.TruckRequest;
import com.company.networkmovers.modules.truck.dto.response.TruckResponse;
import java.util.List;

public interface TruckService {
    TruckResponse create(TruckRequest request);
    TruckResponse findById(Long id);
    List<TruckResponse> findAll();
    TruckResponse update(Long id, TruckRequest request);
    void delete(Long id);
}
