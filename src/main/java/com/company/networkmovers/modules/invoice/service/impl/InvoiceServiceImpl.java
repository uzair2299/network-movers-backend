package com.company.networkmovers.modules.invoice.service.impl;

import com.company.networkmovers.modules.invoice.entity.InvoiceEntity;
import com.company.networkmovers.modules.invoice.repository.InvoiceRepository;
import com.company.networkmovers.modules.invoice.service.InvoiceService;
import com.company.networkmovers.modules.invoice.dto.request.InvoiceRequest;
import com.company.networkmovers.modules.invoice.dto.response.InvoiceResponse;
import com.company.networkmovers.modules.invoice.mapper.InvoiceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository repository;
    private final InvoiceMapper mapper;

    public InvoiceServiceImpl(InvoiceRepository repository, InvoiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public InvoiceResponse create(InvoiceRequest request) {
        InvoiceEntity entity = mapper.toEntity(request);
        InvoiceEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceResponse findById(Long id) {
        InvoiceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceResponse update(Long id, InvoiceRequest request) {
        InvoiceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        InvoiceEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        InvoiceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
        repository.delete(entity);
    }
}
