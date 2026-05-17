package com.company.networkmovers.modules.quotation.service;

import com.company.networkmovers.modules.quotation.dto.request.QuotationRequest;
import com.company.networkmovers.modules.quotation.dto.response.QuotationResponse;
import java.util.List;

public interface QuotationService {
    QuotationResponse create(QuotationRequest request);
    QuotationResponse findById(Long id);
    List<QuotationResponse> findAll();
    QuotationResponse update(Long id, QuotationRequest request);
    void delete(Long id);
}
