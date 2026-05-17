package com.company.networkmovers.modules.quotation.facade;

import com.company.networkmovers.modules.quotation.dto.request.QuotationRequest;
import com.company.networkmovers.modules.quotation.dto.response.QuotationResponse;
import com.company.networkmovers.modules.quotation.service.QuotationService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class QuotationFacade {

    private final QuotationService service;

    public QuotationFacade(QuotationService service) {
        this.service = service;
    }

    public QuotationResponse create(QuotationRequest request) {
        return service.create(request);
    }

    public QuotationResponse findById(Long id) {
        return service.findById(id);
    }

    public List<QuotationResponse> findAll() {
        return service.findAll();
    }

    public QuotationResponse update(Long id, QuotationRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
