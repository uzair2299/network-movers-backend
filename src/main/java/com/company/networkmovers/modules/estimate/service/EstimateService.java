package com.company.networkmovers.modules.estimate.service;

import java.util.UUID;

import com.company.networkmovers.modules.estimate.dto.request.EstimateRequest;
import com.company.networkmovers.modules.estimate.dto.response.EstimateResponse;
import java.util.List;

public interface EstimateService {
    EstimateResponse create(EstimateRequest request);
    EstimateResponse findById(UUID id);
    List<EstimateResponse> findAll();
    EstimateResponse update(UUID id, EstimateRequest request);
    void delete(UUID id);
}
