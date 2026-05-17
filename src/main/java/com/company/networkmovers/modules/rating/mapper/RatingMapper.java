package com.company.networkmovers.modules.rating.mapper;

import com.company.networkmovers.modules.rating.entity.RatingEntity;
import com.company.networkmovers.modules.rating.dto.request.RatingRequest;
import com.company.networkmovers.modules.rating.dto.response.RatingResponse;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {

    public RatingEntity toEntity(RatingRequest request) {
        if (request == null) return null;
        return RatingEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public RatingResponse toResponse(RatingEntity entity) {
        if (entity == null) return null;
        return RatingResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
