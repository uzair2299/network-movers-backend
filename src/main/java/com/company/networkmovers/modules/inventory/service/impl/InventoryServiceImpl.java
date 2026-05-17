package com.company.networkmovers.modules.inventory.service.impl;

import com.company.networkmovers.modules.inventory.entity.InventoryEntity;
import com.company.networkmovers.modules.inventory.repository.InventoryRepository;
import com.company.networkmovers.modules.inventory.service.InventoryService;
import com.company.networkmovers.modules.inventory.dto.request.InventoryRequest;
import com.company.networkmovers.modules.inventory.dto.response.InventoryResponse;
import com.company.networkmovers.modules.inventory.mapper.InventoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final InventoryMapper mapper;

    public InventoryServiceImpl(InventoryRepository repository, InventoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public InventoryResponse create(InventoryRequest request) {
        InventoryEntity entity = mapper.toEntity(request);
        InventoryEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryResponse findById(Long id) {
        InventoryEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryResponse update(Long id, InventoryRequest request) {
        InventoryEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        InventoryEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        InventoryEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));
        repository.delete(entity);
    }
}
