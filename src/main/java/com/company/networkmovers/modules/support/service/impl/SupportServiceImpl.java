package com.company.networkmovers.modules.support.service.impl;

import com.company.networkmovers.modules.support.entity.SupportEntity;
import com.company.networkmovers.modules.support.repository.SupportRepository;
import com.company.networkmovers.modules.support.service.SupportService;
import com.company.networkmovers.modules.support.dto.request.SupportRequest;
import com.company.networkmovers.modules.support.dto.response.SupportResponse;
import com.company.networkmovers.modules.support.mapper.SupportMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupportServiceImpl implements SupportService {

    private final SupportRepository repository;
    private final SupportMapper mapper;

    public SupportServiceImpl(SupportRepository repository, SupportMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SupportResponse create(SupportRequest request) {
        SupportEntity entity = mapper.toEntity(request);
        SupportEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SupportResponse findById(Long id) {
        SupportEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Support not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupportResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SupportResponse update(Long id, SupportRequest request) {
        SupportEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Support not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        SupportEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        SupportEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Support not found with id: " + id));
        repository.delete(entity);
    }
}
