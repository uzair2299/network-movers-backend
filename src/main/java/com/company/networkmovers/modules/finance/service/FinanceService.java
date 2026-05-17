package com.company.networkmovers.modules.finance.service;

import com.company.networkmovers.modules.finance.dto.request.FinanceRequest;
import com.company.networkmovers.modules.finance.dto.response.FinanceResponse;
import java.util.List;

public interface FinanceService {
    FinanceResponse create(FinanceRequest request);
    FinanceResponse findById(Long id);
    List<FinanceResponse> findAll();
    FinanceResponse update(Long id, FinanceRequest request);
    void delete(Long id);
}
