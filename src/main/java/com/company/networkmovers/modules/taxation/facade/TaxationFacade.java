package com.company.networkmovers.modules.taxation.facade;

import com.company.networkmovers.modules.taxation.dto.request.TaxationRequest;
import com.company.networkmovers.modules.taxation.dto.response.TaxationResponse;
import com.company.networkmovers.modules.taxation.service.TaxationService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TaxationFacade {

    private final TaxationService service;

    public TaxationFacade(TaxationService service) {
        this.service = service;
    }

    public TaxationResponse create(TaxationRequest request) {
        return service.create(request);
    }

    public TaxationResponse findById(Long id) {
        return service.findById(id);
    }

    public List<TaxationResponse> findAll() {
        return service.findAll();
    }

    public TaxationResponse update(Long id, TaxationRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
