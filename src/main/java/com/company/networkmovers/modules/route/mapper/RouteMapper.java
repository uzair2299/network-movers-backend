package com.company.networkmovers.modules.route.mapper;

import com.company.networkmovers.modules.route.entity.RouteEntity;
import com.company.networkmovers.modules.route.dto.request.RouteRequest;
import com.company.networkmovers.modules.route.dto.response.RouteResponse;
import org.springframework.stereotype.Component;

@Component
public class RouteMapper {

    public RouteEntity toEntity(RouteRequest request) {
        if (request == null) return null;
        return RouteEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public RouteResponse toResponse(RouteEntity entity) {
        if (entity == null) return null;
        return RouteResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
