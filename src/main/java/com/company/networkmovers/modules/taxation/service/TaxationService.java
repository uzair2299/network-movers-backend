package com.company.networkmovers.modules.taxation.service;

import java.util.UUID;

import com.company.networkmovers.modules.taxation.dto.request.TaxationRequest;
import com.company.networkmovers.modules.taxation.dto.response.TaxationResponse;
import java.util.List;

public interface TaxationService {
    TaxationResponse create(TaxationRequest request);
    TaxationResponse findById(UUID id);
    List<TaxationResponse> findAll();
    TaxationResponse update(UUID id, TaxationRequest request);
    void delete(UUID id);
}
