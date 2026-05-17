package com.company.networkmovers.modules.notification.service;

import com.company.networkmovers.modules.notification.dto.request.NotificationRequest;
import com.company.networkmovers.modules.notification.dto.response.NotificationResponse;
import java.util.List;

public interface NotificationService {
    NotificationResponse create(NotificationRequest request);
    NotificationResponse findById(Long id);
    List<NotificationResponse> findAll();
    NotificationResponse update(Long id, NotificationRequest request);
    void delete(Long id);
}
