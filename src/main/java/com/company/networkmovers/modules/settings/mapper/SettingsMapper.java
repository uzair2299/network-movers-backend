package com.company.networkmovers.modules.settings.mapper;

import com.company.networkmovers.modules.settings.entity.SettingsEntity;
import com.company.networkmovers.modules.settings.dto.request.SettingsRequest;
import com.company.networkmovers.modules.settings.dto.response.SettingsResponse;
import org.springframework.stereotype.Component;

@Component
public class SettingsMapper {

    public SettingsEntity toEntity(SettingsRequest request) {
        if (request == null) return null;
        return SettingsEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public SettingsResponse toResponse(SettingsEntity entity) {
        if (entity == null) return null;
        return SettingsResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
