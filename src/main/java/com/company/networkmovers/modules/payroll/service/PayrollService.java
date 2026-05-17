package com.company.networkmovers.modules.payroll.service;

import com.company.networkmovers.modules.payroll.dto.request.PayrollRequest;
import com.company.networkmovers.modules.payroll.dto.response.PayrollResponse;
import java.util.List;

public interface PayrollService {
    PayrollResponse create(PayrollRequest request);
    PayrollResponse findById(Long id);
    List<PayrollResponse> findAll();
    PayrollResponse update(Long id, PayrollRequest request);
    void delete(Long id);
}
