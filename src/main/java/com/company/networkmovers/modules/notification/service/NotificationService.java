package com.company.networkmovers.modules.notification.service;

import java.util.UUID;

import com.company.networkmovers.modules.notification.dto.request.NotificationRequest;
import com.company.networkmovers.modules.notification.dto.response.NotificationResponse;
import java.util.List;

public interface NotificationService {
    NotificationResponse create(NotificationRequest request);
    NotificationResponse findById(UUID id);
    List<NotificationResponse> findAll();
    NotificationResponse update(UUID id, NotificationRequest request);
    void delete(UUID id);
}
