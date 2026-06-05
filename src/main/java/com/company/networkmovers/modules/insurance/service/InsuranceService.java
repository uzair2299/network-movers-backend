package com.company.networkmovers.modules.insurance.service;

import java.util.UUID;

import com.company.networkmovers.modules.insurance.dto.request.InsuranceRequest;
import com.company.networkmovers.modules.insurance.dto.response.InsuranceResponse;
import java.util.List;

public interface InsuranceService {
    InsuranceResponse create(InsuranceRequest request);
    InsuranceResponse findById(UUID id);
    List<InsuranceResponse> findAll();
    InsuranceResponse update(UUID id, InsuranceRequest request);
    void delete(UUID id);
}
