package com.company.networkmovers.modules.lookup.service.impl;

import com.company.networkmovers.modules.lookup.entity.LookupEntity;
import com.company.networkmovers.modules.lookup.repository.LookupRepository;
import com.company.networkmovers.modules.lookup.service.LookupService;
import com.company.networkmovers.modules.lookup.dto.request.LookupRequest;
import com.company.networkmovers.modules.lookup.dto.response.LookupResponse;
import com.company.networkmovers.modules.lookup.mapper.LookupMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LookupServiceImpl implements LookupService {

    private final LookupRepository repository;
    private final LookupMapper mapper;

    public LookupServiceImpl(LookupRepository repository, LookupMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public LookupResponse create(LookupRequest request) {
        LookupEntity entity = mapper.toEntity(request);
        LookupEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public LookupResponse findById(Long id) {
        LookupEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lookup not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LookupResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LookupResponse update(Long id, LookupRequest request) {
        LookupEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lookup not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        LookupEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        LookupEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lookup not found with id: " + id));
        repository.delete(entity);
    }
}
