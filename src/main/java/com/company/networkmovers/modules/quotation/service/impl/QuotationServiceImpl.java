package com.company.networkmovers.modules.quotation.service.impl;

import com.company.networkmovers.modules.quotation.entity.QuotationEntity;
import com.company.networkmovers.modules.quotation.repository.QuotationRepository;
import com.company.networkmovers.modules.quotation.service.QuotationService;
import com.company.networkmovers.modules.quotation.dto.request.QuotationRequest;
import com.company.networkmovers.modules.quotation.dto.response.QuotationResponse;
import com.company.networkmovers.modules.quotation.mapper.QuotationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuotationServiceImpl implements QuotationService {

    private final QuotationRepository repository;
    private final QuotationMapper mapper;

    public QuotationServiceImpl(QuotationRepository repository, QuotationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public QuotationResponse create(QuotationRequest request) {
        QuotationEntity entity = mapper.toEntity(request);
        QuotationEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public QuotationResponse findById(Long id) {
        QuotationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quotation not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuotationResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public QuotationResponse update(Long id, QuotationRequest request) {
        QuotationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quotation not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        QuotationEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        QuotationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quotation not found with id: " + id));
        repository.delete(entity);
    }
}
