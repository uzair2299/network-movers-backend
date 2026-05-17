package com.company.networkmovers.modules.driver.service.impl;

import com.company.networkmovers.modules.driver.entity.DriverEntity;
import com.company.networkmovers.modules.driver.repository.DriverRepository;
import com.company.networkmovers.modules.driver.service.DriverService;
import com.company.networkmovers.modules.driver.dto.request.DriverRequest;
import com.company.networkmovers.modules.driver.dto.response.DriverResponse;
import com.company.networkmovers.modules.driver.mapper.DriverMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private final DriverRepository repository;
    private final DriverMapper mapper;

    public DriverServiceImpl(DriverRepository repository, DriverMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DriverResponse create(DriverRequest request) {
        DriverEntity entity = mapper.toEntity(request);
        DriverEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DriverResponse findById(Long id) {
        DriverEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DriverResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DriverResponse update(Long id, DriverRequest request) {
        DriverEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        DriverEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        DriverEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));
        repository.delete(entity);
    }
}
