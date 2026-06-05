package com.company.networkmovers.modules.finance.facade;

import java.util.UUID;

import com.company.networkmovers.modules.finance.dto.request.FinanceRequest;
import com.company.networkmovers.modules.finance.dto.response.FinanceResponse;
import com.company.networkmovers.modules.finance.service.FinanceService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FinanceFacade {

    private final FinanceService service;

    public FinanceFacade(FinanceService service) {
        this.service = service;
    }

    public FinanceResponse create(FinanceRequest request) {
        return service.create(request);
    }

    public FinanceResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<FinanceResponse> findAll() {
        return service.findAll();
    }

    public FinanceResponse update(UUID id, FinanceRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
