package com.company.networkmovers.modules.hr.service.impl;

import com.company.networkmovers.modules.hr.entity.HrEntity;
import com.company.networkmovers.modules.hr.repository.HrRepository;
import com.company.networkmovers.modules.hr.service.HrService;
import com.company.networkmovers.modules.hr.dto.request.HrRequest;
import com.company.networkmovers.modules.hr.dto.response.HrResponse;
import com.company.networkmovers.modules.hr.mapper.HrMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HrServiceImpl implements HrService {

    private final HrRepository repository;
    private final HrMapper mapper;

    public HrServiceImpl(HrRepository repository, HrMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public HrResponse create(HrRequest request) {
        HrEntity entity = mapper.toEntity(request);
        HrEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public HrResponse findById(Long id) {
        HrEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hr not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HrResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public HrResponse update(Long id, HrRequest request) {
        HrEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hr not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        HrEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        HrEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hr not found with id: " + id));
        repository.delete(entity);
    }
}
