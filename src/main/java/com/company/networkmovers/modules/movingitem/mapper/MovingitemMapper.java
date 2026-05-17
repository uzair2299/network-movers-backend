package com.company.networkmovers.modules.movingitem.mapper;

import com.company.networkmovers.modules.movingitem.entity.MovingitemEntity;
import com.company.networkmovers.modules.movingitem.dto.request.MovingitemRequest;
import com.company.networkmovers.modules.movingitem.dto.response.MovingitemResponse;
import org.springframework.stereotype.Component;

@Component
public class MovingitemMapper {

    public MovingitemEntity toEntity(MovingitemRequest request) {
        if (request == null) return null;
        return MovingitemEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public MovingitemResponse toResponse(MovingitemEntity entity) {
        if (entity == null) return null;
        return MovingitemResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
