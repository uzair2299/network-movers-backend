package com.company.networkmovers.modules.quotation.service;

import java.util.UUID;

import com.company.networkmovers.modules.quotation.dto.request.QuotationRequest;
import com.company.networkmovers.modules.quotation.dto.response.QuotationResponse;
import java.util.List;

public interface QuotationService {
    QuotationResponse create(QuotationRequest request);
    QuotationResponse findById(UUID id);
    List<QuotationResponse> findAll();
    QuotationResponse update(UUID id, QuotationRequest request);
    void delete(UUID id);
}
