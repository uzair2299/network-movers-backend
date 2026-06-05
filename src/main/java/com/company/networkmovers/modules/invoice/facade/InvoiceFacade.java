package com.company.networkmovers.modules.invoice.facade;

import java.util.UUID;

import com.company.networkmovers.modules.invoice.dto.request.InvoiceRequest;
import com.company.networkmovers.modules.invoice.dto.response.InvoiceResponse;
import com.company.networkmovers.modules.invoice.service.InvoiceService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class InvoiceFacade {

    private final InvoiceService service;

    public InvoiceFacade(InvoiceService service) {
        this.service = service;
    }

    public InvoiceResponse create(InvoiceRequest request) {
        return service.create(request);
    }

    public InvoiceResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<InvoiceResponse> findAll() {
        return service.findAll();
    }

    public InvoiceResponse update(UUID id, InvoiceRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
