package com.company.networkmovers.modules.invoice.service;

import java.util.UUID;

import com.company.networkmovers.modules.invoice.dto.request.InvoiceRequest;
import com.company.networkmovers.modules.invoice.dto.response.InvoiceResponse;
import java.util.List;

public interface InvoiceService {
    InvoiceResponse create(InvoiceRequest request);
    InvoiceResponse findById(UUID id);
    List<InvoiceResponse> findAll();
    InvoiceResponse update(UUID id, InvoiceRequest request);
    void delete(UUID id);
}
