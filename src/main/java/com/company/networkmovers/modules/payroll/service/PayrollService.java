package com.company.networkmovers.modules.payroll.service;

import java.util.UUID;

import com.company.networkmovers.modules.payroll.dto.request.PayrollRequest;
import com.company.networkmovers.modules.payroll.dto.response.PayrollResponse;
import java.util.List;

public interface PayrollService {
    PayrollResponse create(PayrollRequest request);
    PayrollResponse findById(UUID id);
    List<PayrollResponse> findAll();
    PayrollResponse update(UUID id, PayrollRequest request);
    void delete(UUID id);
}
