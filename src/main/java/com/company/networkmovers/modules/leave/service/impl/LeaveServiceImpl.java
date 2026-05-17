package com.company.networkmovers.modules.leave.service.impl;

import com.company.networkmovers.modules.leave.entity.LeaveEntity;
import com.company.networkmovers.modules.leave.repository.LeaveRepository;
import com.company.networkmovers.modules.leave.service.LeaveService;
import com.company.networkmovers.modules.leave.dto.request.LeaveRequest;
import com.company.networkmovers.modules.leave.dto.response.LeaveResponse;
import com.company.networkmovers.modules.leave.mapper.LeaveMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository repository;
    private final LeaveMapper mapper;

    public LeaveServiceImpl(LeaveRepository repository, LeaveMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public LeaveResponse create(LeaveRequest request) {
        LeaveEntity entity = mapper.toEntity(request);
        LeaveEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public LeaveResponse findById(Long id) {
        LeaveEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LeaveResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LeaveResponse update(Long id, LeaveRequest request) {
        LeaveEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        LeaveEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        LeaveEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found with id: " + id));
        repository.delete(entity);
    }
}
