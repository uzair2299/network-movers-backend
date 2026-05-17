package com.company.networkmovers.modules.invoice.service;

import com.company.networkmovers.modules.invoice.dto.request.InvoiceRequest;
import com.company.networkmovers.modules.invoice.dto.response.InvoiceResponse;
import java.util.List;

public interface InvoiceService {
    InvoiceResponse create(InvoiceRequest request);
    InvoiceResponse findById(Long id);
    List<InvoiceResponse> findAll();
    InvoiceResponse update(Long id, InvoiceRequest request);
    void delete(Long id);
}
