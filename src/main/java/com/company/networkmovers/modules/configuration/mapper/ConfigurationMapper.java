package com.company.networkmovers.modules.configuration.mapper;

import com.company.networkmovers.modules.configuration.entity.ConfigurationEntity;
import com.company.networkmovers.modules.configuration.dto.request.ConfigurationRequest;
import com.company.networkmovers.modules.configuration.dto.response.ConfigurationResponse;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationMapper {

    public ConfigurationEntity toEntity(ConfigurationRequest request) {
        if (request == null) return null;
        return ConfigurationEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public ConfigurationResponse toResponse(ConfigurationEntity entity) {
        if (entity == null) return null;
        return ConfigurationResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
