package com.company.networkmovers.modules.insurance.facade;

import com.company.networkmovers.modules.insurance.dto.request.InsuranceRequest;
import com.company.networkmovers.modules.insurance.dto.response.InsuranceResponse;
import com.company.networkmovers.modules.insurance.service.InsuranceService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class InsuranceFacade {

    private final InsuranceService service;

    public InsuranceFacade(InsuranceService service) {
        this.service = service;
    }

    public InsuranceResponse create(InsuranceRequest request) {
        return service.create(request);
    }

    public InsuranceResponse findById(Long id) {
        return service.findById(id);
    }

    public List<InsuranceResponse> findAll() {
        return service.findAll();
    }

    public InsuranceResponse update(Long id, InsuranceRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
