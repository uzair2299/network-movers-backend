package com.company.networkmovers.modules.hr.mapper;

import com.company.networkmovers.modules.hr.entity.HrEntity;
import com.company.networkmovers.modules.hr.dto.request.HrRequest;
import com.company.networkmovers.modules.hr.dto.response.HrResponse;
import org.springframework.stereotype.Component;

@Component
public class HrMapper {

    public HrEntity toEntity(HrRequest request) {
        if (request == null) return null;
        return HrEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public HrResponse toResponse(HrEntity entity) {
        if (entity == null) return null;
        return HrResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
