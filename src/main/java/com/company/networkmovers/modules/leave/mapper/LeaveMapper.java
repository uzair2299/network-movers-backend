package com.company.networkmovers.modules.leave.mapper;

import com.company.networkmovers.modules.leave.entity.LeaveEntity;
import com.company.networkmovers.modules.leave.dto.request.LeaveRequest;
import com.company.networkmovers.modules.leave.dto.response.LeaveResponse;
import org.springframework.stereotype.Component;

@Component
public class LeaveMapper {

    public LeaveEntity toEntity(LeaveRequest request) {
        if (request == null) return null;
        return LeaveEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public LeaveResponse toResponse(LeaveEntity entity) {
        if (entity == null) return null;
        return LeaveResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
