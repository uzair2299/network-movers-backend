package com.company.networkmovers.modules.payment.service.impl;

import com.company.networkmovers.modules.payment.entity.PaymentEntity;
import com.company.networkmovers.modules.payment.repository.PaymentRepository;
import com.company.networkmovers.modules.payment.service.PaymentService;
import com.company.networkmovers.modules.payment.dto.request.PaymentRequest;
import com.company.networkmovers.modules.payment.dto.response.PaymentResponse;
import com.company.networkmovers.modules.payment.mapper.PaymentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    public PaymentServiceImpl(PaymentRepository repository, PaymentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PaymentResponse create(PaymentRequest request) {
        PaymentEntity entity = mapper.toEntity(request);
        PaymentEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse findById(Long id) {
        PaymentEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponse update(Long id, PaymentRequest request) {
        PaymentEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        PaymentEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        PaymentEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        repository.delete(entity);
    }
}
