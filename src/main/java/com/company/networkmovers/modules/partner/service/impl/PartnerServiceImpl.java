package com.company.networkmovers.modules.partner.service.impl;

import com.company.networkmovers.modules.partner.entity.PartnerEntity;
import com.company.networkmovers.modules.partner.repository.PartnerRepository;
import com.company.networkmovers.modules.partner.service.PartnerService;
import com.company.networkmovers.modules.partner.dto.request.PartnerRequest;
import com.company.networkmovers.modules.partner.dto.response.PartnerResponse;
import com.company.networkmovers.modules.partner.mapper.PartnerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository repository;
    private final PartnerMapper mapper;

    public PartnerServiceImpl(PartnerRepository repository, PartnerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PartnerResponse create(PartnerRequest request) {
        PartnerEntity entity = mapper.toEntity(request);
        PartnerEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PartnerResponse findById(Long id) {
        PartnerEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partner not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartnerResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PartnerResponse update(Long id, PartnerRequest request) {
        PartnerEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partner not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        PartnerEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        PartnerEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partner not found with id: " + id));
        repository.delete(entity);
    }
}
