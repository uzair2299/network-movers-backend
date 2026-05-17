package com.company.networkmovers.modules.attendance.mapper;

import com.company.networkmovers.modules.attendance.entity.AttendanceEntity;
import com.company.networkmovers.modules.attendance.dto.request.AttendanceRequest;
import com.company.networkmovers.modules.attendance.dto.response.AttendanceResponse;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {

    public AttendanceEntity toEntity(AttendanceRequest request) {
        if (request == null) return null;
        return AttendanceEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public AttendanceResponse toResponse(AttendanceEntity entity) {
        if (entity == null) return null;
        return AttendanceResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
