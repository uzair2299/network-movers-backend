package com.company.networkmovers.modules.automation.mapper;

import com.company.networkmovers.modules.automation.entity.AutomationEntity;
import com.company.networkmovers.modules.automation.dto.request.AutomationRequest;
import com.company.networkmovers.modules.automation.dto.response.AutomationResponse;
import org.springframework.stereotype.Component;

@Component
public class AutomationMapper {

    public AutomationEntity toEntity(AutomationRequest request) {
        if (request == null) return null;
        return AutomationEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public AutomationResponse toResponse(AutomationEntity entity) {
        if (entity == null) return null;
        return AutomationResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
