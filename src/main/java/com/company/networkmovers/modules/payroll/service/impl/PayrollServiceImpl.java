package com.company.networkmovers.modules.payroll.service.impl;

import com.company.networkmovers.modules.payroll.entity.PayrollEntity;
import com.company.networkmovers.modules.payroll.repository.PayrollRepository;
import com.company.networkmovers.modules.payroll.service.PayrollService;
import com.company.networkmovers.modules.payroll.dto.request.PayrollRequest;
import com.company.networkmovers.modules.payroll.dto.response.PayrollResponse;
import com.company.networkmovers.modules.payroll.mapper.PayrollMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PayrollServiceImpl implements PayrollService {

    private final PayrollRepository repository;
    private final PayrollMapper mapper;

    public PayrollServiceImpl(PayrollRepository repository, PayrollMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PayrollResponse create(PayrollRequest request) {
        PayrollEntity entity = mapper.toEntity(request);
        PayrollEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PayrollResponse findById(Long id) {
        PayrollEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PayrollResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PayrollResponse update(Long id, PayrollRequest request) {
        PayrollEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        PayrollEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        PayrollEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found with id: " + id));
        repository.delete(entity);
    }
}
