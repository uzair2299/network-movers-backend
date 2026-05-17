package com.company.networkmovers.modules.insurance.service;

import com.company.networkmovers.modules.insurance.dto.request.InsuranceRequest;
import com.company.networkmovers.modules.insurance.dto.response.InsuranceResponse;
import java.util.List;

public interface InsuranceService {
    InsuranceResponse create(InsuranceRequest request);
    InsuranceResponse findById(Long id);
    List<InsuranceResponse> findAll();
    InsuranceResponse update(Long id, InsuranceRequest request);
    void delete(Long id);
}
