package com.company.networkmovers.modules.taxation.service.impl;

import com.company.networkmovers.modules.taxation.entity.TaxationEntity;
import com.company.networkmovers.modules.taxation.repository.TaxationRepository;
import com.company.networkmovers.modules.taxation.service.TaxationService;
import com.company.networkmovers.modules.taxation.dto.request.TaxationRequest;
import com.company.networkmovers.modules.taxation.dto.response.TaxationResponse;
import com.company.networkmovers.modules.taxation.mapper.TaxationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaxationServiceImpl implements TaxationService {

    private final TaxationRepository repository;
    private final TaxationMapper mapper;

    public TaxationServiceImpl(TaxationRepository repository, TaxationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TaxationResponse create(TaxationRequest request) {
        TaxationEntity entity = mapper.toEntity(request);
        TaxationEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TaxationResponse findById(Long id) {
        TaxationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Taxation not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaxationResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TaxationResponse update(Long id, TaxationRequest request) {
        TaxationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Taxation not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        TaxationEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        TaxationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Taxation not found with id: " + id));
        repository.delete(entity);
    }
}
