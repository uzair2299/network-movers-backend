package com.company.networkmovers.modules.maps.mapper;

import com.company.networkmovers.modules.maps.entity.MapsEntity;
import com.company.networkmovers.modules.maps.dto.request.MapsRequest;
import com.company.networkmovers.modules.maps.dto.response.MapsResponse;
import org.springframework.stereotype.Component;

@Component
public class MapsMapper {

    public MapsEntity toEntity(MapsRequest request) {
        if (request == null) return null;
        return MapsEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public MapsResponse toResponse(MapsEntity entity) {
        if (entity == null) return null;
        return MapsResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
