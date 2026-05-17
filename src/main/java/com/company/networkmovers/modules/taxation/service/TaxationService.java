package com.company.networkmovers.modules.taxation.service;

import com.company.networkmovers.modules.taxation.dto.request.TaxationRequest;
import com.company.networkmovers.modules.taxation.dto.response.TaxationResponse;
import java.util.List;

public interface TaxationService {
    TaxationResponse create(TaxationRequest request);
    TaxationResponse findById(Long id);
    List<TaxationResponse> findAll();
    TaxationResponse update(Long id, TaxationRequest request);
    void delete(Long id);
}
