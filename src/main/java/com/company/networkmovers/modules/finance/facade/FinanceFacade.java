package com.company.networkmovers.modules.finance.facade;

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

    public FinanceResponse findById(Long id) {
        return service.findById(id);
    }

    public List<FinanceResponse> findAll() {
        return service.findAll();
    }

    public FinanceResponse update(Long id, FinanceRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
