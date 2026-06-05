package com.company.networkmovers.modules.finance.service;

import java.util.UUID;

import com.company.networkmovers.modules.finance.dto.request.FinanceRequest;
import com.company.networkmovers.modules.finance.dto.response.FinanceResponse;
import java.util.List;

public interface FinanceService {
    FinanceResponse create(FinanceRequest request);
    FinanceResponse findById(UUID id);
    List<FinanceResponse> findAll();
    FinanceResponse update(UUID id, FinanceRequest request);
    void delete(UUID id);
}
