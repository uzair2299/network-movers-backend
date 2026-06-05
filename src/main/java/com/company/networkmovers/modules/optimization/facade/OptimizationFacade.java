package com.company.networkmovers.modules.optimization.facade;

import java.util.UUID;

import com.company.networkmovers.modules.optimization.dto.request.OptimizationRequest;
import com.company.networkmovers.modules.optimization.dto.response.OptimizationResponse;
import com.company.networkmovers.modules.optimization.service.OptimizationService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OptimizationFacade {

    private final OptimizationService service;

    public OptimizationFacade(OptimizationService service) {
        this.service = service;
    }

    public OptimizationResponse create(OptimizationRequest request) {
        return service.create(request);
    }

    public OptimizationResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<OptimizationResponse> findAll() {
        return service.findAll();
    }

    public OptimizationResponse update(UUID id, OptimizationRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
