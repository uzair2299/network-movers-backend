package com.company.networkmovers.modules.estimate.service;

import com.company.networkmovers.modules.estimate.dto.request.EstimateRequest;
import com.company.networkmovers.modules.estimate.dto.response.EstimateResponse;
import java.util.List;

public interface EstimateService {
    EstimateResponse create(EstimateRequest request);
    EstimateResponse findById(Long id);
    List<EstimateResponse> findAll();
    EstimateResponse update(Long id, EstimateRequest request);
    void delete(Long id);
}
