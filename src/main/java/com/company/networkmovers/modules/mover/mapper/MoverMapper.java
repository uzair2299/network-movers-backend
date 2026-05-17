package com.company.networkmovers.modules.mover.mapper;

import com.company.networkmovers.modules.mover.entity.MoverEntity;
import com.company.networkmovers.modules.mover.dto.request.MoverRequest;
import com.company.networkmovers.modules.mover.dto.response.MoverResponse;
import org.springframework.stereotype.Component;

@Component
public class MoverMapper {

    public MoverEntity toEntity(MoverRequest request) {
        if (request == null) return null;
        return MoverEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public MoverResponse toResponse(MoverEntity entity) {
        if (entity == null) return null;
        return MoverResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
