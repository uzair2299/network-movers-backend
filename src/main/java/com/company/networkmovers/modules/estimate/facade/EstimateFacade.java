package com.company.networkmovers.modules.estimate.facade;

import com.company.networkmovers.modules.estimate.dto.request.EstimateRequest;
import com.company.networkmovers.modules.estimate.dto.response.EstimateResponse;
import com.company.networkmovers.modules.estimate.service.EstimateService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class EstimateFacade {

    private final EstimateService service;

    public EstimateFacade(EstimateService service) {
        this.service = service;
    }

    public EstimateResponse create(EstimateRequest request) {
        return service.create(request);
    }

    public EstimateResponse findById(Long id) {
        return service.findById(id);
    }

    public List<EstimateResponse> findAll() {
        return service.findAll();
    }

    public EstimateResponse update(Long id, EstimateRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
