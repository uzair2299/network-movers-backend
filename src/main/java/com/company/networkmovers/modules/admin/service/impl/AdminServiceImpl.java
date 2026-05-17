package com.company.networkmovers.modules.admin.service.impl;

import com.company.networkmovers.modules.admin.entity.AdminEntity;
import com.company.networkmovers.modules.admin.repository.AdminRepository;
import com.company.networkmovers.modules.admin.service.AdminService;
import com.company.networkmovers.modules.admin.dto.request.AdminRequest;
import com.company.networkmovers.modules.admin.dto.response.AdminResponse;
import com.company.networkmovers.modules.admin.mapper.AdminMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepository repository;
    private final AdminMapper mapper;

    public AdminServiceImpl(AdminRepository repository, AdminMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AdminResponse create(AdminRequest request) {
        AdminEntity entity = mapper.toEntity(request);
        AdminEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AdminResponse findById(Long id) {
        AdminEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdminResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AdminResponse update(Long id, AdminRequest request) {
        AdminEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        AdminEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        AdminEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
        repository.delete(entity);
    }
}
