package com.company.networkmovers.modules.notification.facade;

import com.company.networkmovers.modules.notification.dto.request.NotificationRequest;
import com.company.networkmovers.modules.notification.dto.response.NotificationResponse;
import com.company.networkmovers.modules.notification.service.NotificationService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class NotificationFacade {

    private final NotificationService service;

    public NotificationFacade(NotificationService service) {
        this.service = service;
    }

    public NotificationResponse create(NotificationRequest request) {
        return service.create(request);
    }

    public NotificationResponse findById(Long id) {
        return service.findById(id);
    }

    public List<NotificationResponse> findAll() {
        return service.findAll();
    }

    public NotificationResponse update(Long id, NotificationRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
