package com.company.networkmovers.modules.dispatcher.mapper;

import com.company.networkmovers.modules.dispatcher.entity.DispatcherEntity;
import com.company.networkmovers.modules.dispatcher.dto.request.DispatcherRequest;
import com.company.networkmovers.modules.dispatcher.dto.response.DispatcherResponse;
import org.springframework.stereotype.Component;

@Component
public class DispatcherMapper {

    public DispatcherEntity toEntity(DispatcherRequest request) {
        if (request == null) return null;
        return DispatcherEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public DispatcherResponse toResponse(DispatcherEntity entity) {
        if (entity == null) return null;
        return DispatcherResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
