package com.company.networkmovers.modules.report.service.impl;

import com.company.networkmovers.modules.report.entity.ReportEntity;
import com.company.networkmovers.modules.report.repository.ReportRepository;
import com.company.networkmovers.modules.report.service.ReportService;
import com.company.networkmovers.modules.report.dto.request.ReportRequest;
import com.company.networkmovers.modules.report.dto.response.ReportResponse;
import com.company.networkmovers.modules.report.mapper.ReportMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    private final ReportRepository repository;
    private final ReportMapper mapper;

    public ReportServiceImpl(ReportRepository repository, ReportMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ReportResponse create(ReportRequest request) {
        ReportEntity entity = mapper.toEntity(request);
        ReportEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ReportResponse findById(Long id) {
        ReportEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReportResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReportResponse update(Long id, ReportRequest request) {
        ReportEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        ReportEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        ReportEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
        repository.delete(entity);
    }
}
