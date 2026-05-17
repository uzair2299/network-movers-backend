package com.company.networkmovers.modules.claims.service.impl;

import com.company.networkmovers.modules.claims.entity.ClaimsEntity;
import com.company.networkmovers.modules.claims.repository.ClaimsRepository;
import com.company.networkmovers.modules.claims.service.ClaimsService;
import com.company.networkmovers.modules.claims.dto.request.ClaimsRequest;
import com.company.networkmovers.modules.claims.dto.response.ClaimsResponse;
import com.company.networkmovers.modules.claims.mapper.ClaimsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClaimsServiceImpl implements ClaimsService {

    private final ClaimsRepository repository;
    private final ClaimsMapper mapper;

    public ClaimsServiceImpl(ClaimsRepository repository, ClaimsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ClaimsResponse create(ClaimsRequest request) {
        ClaimsEntity entity = mapper.toEntity(request);
        ClaimsEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ClaimsResponse findById(Long id) {
        ClaimsEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claims not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClaimsResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClaimsResponse update(Long id, ClaimsRequest request) {
        ClaimsEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claims not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        ClaimsEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        ClaimsEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claims not found with id: " + id));
        repository.delete(entity);
    }
}
