package com.company.networkmovers.modules.dashboard.mapper;

import com.company.networkmovers.modules.dashboard.entity.DashboardEntity;
import com.company.networkmovers.modules.dashboard.dto.request.DashboardRequest;
import com.company.networkmovers.modules.dashboard.dto.response.DashboardResponse;
import org.springframework.stereotype.Component;

@Component
public class DashboardMapper {

    public DashboardEntity toEntity(DashboardRequest request) {
        if (request == null) return null;
        return DashboardEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public DashboardResponse toResponse(DashboardEntity entity) {
        if (entity == null) return null;
        return DashboardResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
