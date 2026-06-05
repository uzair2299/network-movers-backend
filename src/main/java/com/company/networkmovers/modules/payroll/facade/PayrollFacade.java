package com.company.networkmovers.modules.payroll.facade;

import java.util.UUID;

import com.company.networkmovers.modules.payroll.dto.request.PayrollRequest;
import com.company.networkmovers.modules.payroll.dto.response.PayrollResponse;
import com.company.networkmovers.modules.payroll.service.PayrollService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PayrollFacade {

    private final PayrollService service;

    public PayrollFacade(PayrollService service) {
        this.service = service;
    }

    public PayrollResponse create(PayrollRequest request) {
        return service.create(request);
    }

    public PayrollResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<PayrollResponse> findAll() {
        return service.findAll();
    }

    public PayrollResponse update(UUID id, PayrollRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
