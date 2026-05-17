package com.company.networkmovers.modules.scheduling.mapper;

import com.company.networkmovers.modules.scheduling.entity.SchedulingEntity;
import com.company.networkmovers.modules.scheduling.dto.request.SchedulingRequest;
import com.company.networkmovers.modules.scheduling.dto.response.SchedulingResponse;
import org.springframework.stereotype.Component;

@Component
public class SchedulingMapper {

    public SchedulingEntity toEntity(SchedulingRequest request) {
        if (request == null) return null;
        return SchedulingEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public SchedulingResponse toResponse(SchedulingEntity entity) {
        if (entity == null) return null;
        return SchedulingResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
