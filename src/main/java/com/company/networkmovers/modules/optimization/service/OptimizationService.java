package com.company.networkmovers.modules.optimization.service;

import java.util.UUID;

import com.company.networkmovers.modules.optimization.dto.request.OptimizationRequest;
import com.company.networkmovers.modules.optimization.dto.response.OptimizationResponse;
import java.util.List;

public interface OptimizationService {
    OptimizationResponse create(OptimizationRequest request);
    OptimizationResponse findById(UUID id);
    List<OptimizationResponse> findAll();
    OptimizationResponse update(UUID id, OptimizationRequest request);
    void delete(UUID id);
}
