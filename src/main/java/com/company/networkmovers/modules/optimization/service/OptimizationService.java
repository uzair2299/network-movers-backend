package com.company.networkmovers.modules.optimization.service;

import com.company.networkmovers.modules.optimization.dto.request.OptimizationRequest;
import com.company.networkmovers.modules.optimization.dto.response.OptimizationResponse;
import java.util.List;

public interface OptimizationService {
    OptimizationResponse create(OptimizationRequest request);
    OptimizationResponse findById(Long id);
    List<OptimizationResponse> findAll();
    OptimizationResponse update(Long id, OptimizationRequest request);
    void delete(Long id);
}
