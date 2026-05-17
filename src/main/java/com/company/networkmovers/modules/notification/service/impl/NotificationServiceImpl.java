package com.company.networkmovers.modules.notification.service.impl;

import com.company.networkmovers.modules.notification.entity.NotificationEntity;
import com.company.networkmovers.modules.notification.repository.NotificationRepository;
import com.company.networkmovers.modules.notification.service.NotificationService;
import com.company.networkmovers.modules.notification.dto.request.NotificationRequest;
import com.company.networkmovers.modules.notification.dto.response.NotificationResponse;
import com.company.networkmovers.modules.notification.mapper.NotificationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final NotificationMapper mapper;

    public NotificationServiceImpl(NotificationRepository repository, NotificationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public NotificationResponse create(NotificationRequest request) {
        NotificationEntity entity = mapper.toEntity(request);
        NotificationEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationResponse findById(Long id) {
        NotificationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponse update(Long id, NotificationRequest request) {
        NotificationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        NotificationEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        NotificationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
        repository.delete(entity);
    }
}
