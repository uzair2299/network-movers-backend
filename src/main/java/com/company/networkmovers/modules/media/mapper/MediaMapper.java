package com.company.networkmovers.modules.media.mapper;

import com.company.networkmovers.modules.media.entity.MediaEntity;
import com.company.networkmovers.modules.media.dto.request.MediaRequest;
import com.company.networkmovers.modules.media.dto.response.MediaResponse;
import org.springframework.stereotype.Component;

@Component
public class MediaMapper {

    public MediaEntity toEntity(MediaRequest request) {
        if (request == null) return null;
        return MediaEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public MediaResponse toResponse(MediaEntity entity) {
        if (entity == null) return null;
        return MediaResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
