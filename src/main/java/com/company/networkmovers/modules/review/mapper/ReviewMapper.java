package com.company.networkmovers.modules.review.mapper;

import com.company.networkmovers.modules.review.entity.ReviewEntity;
import com.company.networkmovers.modules.review.dto.request.ReviewRequest;
import com.company.networkmovers.modules.review.dto.response.ReviewResponse;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewEntity toEntity(ReviewRequest request) {
        if (request == null) return null;
        return ReviewEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public ReviewResponse toResponse(ReviewEntity entity) {
        if (entity == null) return null;
        return ReviewResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
