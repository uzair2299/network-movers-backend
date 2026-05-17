package com.company.networkmovers.modules.realtime.mapper;

import com.company.networkmovers.modules.realtime.entity.RealtimeEntity;
import com.company.networkmovers.modules.realtime.dto.request.RealtimeRequest;
import com.company.networkmovers.modules.realtime.dto.response.RealtimeResponse;
import org.springframework.stereotype.Component;

@Component
public class RealtimeMapper {

    public RealtimeEntity toEntity(RealtimeRequest request) {
        if (request == null) return null;
        return RealtimeEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public RealtimeResponse toResponse(RealtimeEntity entity) {
        if (entity == null) return null;
        return RealtimeResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
