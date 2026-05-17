package com.company.networkmovers.modules.notification.mapper;

import com.company.networkmovers.modules.notification.entity.NotificationEntity;
import com.company.networkmovers.modules.notification.dto.request.NotificationRequest;
import com.company.networkmovers.modules.notification.dto.response.NotificationResponse;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationEntity toEntity(NotificationRequest request) {
        if (request == null) return null;
        return NotificationEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public NotificationResponse toResponse(NotificationEntity entity) {
        if (entity == null) return null;
        return NotificationResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
