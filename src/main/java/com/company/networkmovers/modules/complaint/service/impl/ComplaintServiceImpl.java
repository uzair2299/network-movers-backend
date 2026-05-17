package com.company.networkmovers.modules.complaint.service.impl;

import com.company.networkmovers.modules.complaint.entity.ComplaintEntity;
import com.company.networkmovers.modules.complaint.repository.ComplaintRepository;
import com.company.networkmovers.modules.complaint.service.ComplaintService;
import com.company.networkmovers.modules.complaint.dto.request.ComplaintRequest;
import com.company.networkmovers.modules.complaint.dto.response.ComplaintResponse;
import com.company.networkmovers.modules.complaint.mapper.ComplaintMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository repository;
    private final ComplaintMapper mapper;

    public ComplaintServiceImpl(ComplaintRepository repository, ComplaintMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ComplaintResponse create(ComplaintRequest request) {
        ComplaintEntity entity = mapper.toEntity(request);
        ComplaintEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ComplaintResponse findById(Long id) {
        ComplaintEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComplaintResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ComplaintResponse update(Long id, ComplaintRequest request) {
        ComplaintEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        ComplaintEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        ComplaintEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + id));
        repository.delete(entity);
    }
}
