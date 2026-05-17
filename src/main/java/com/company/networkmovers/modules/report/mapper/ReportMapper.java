package com.company.networkmovers.modules.report.mapper;

import com.company.networkmovers.modules.report.entity.ReportEntity;
import com.company.networkmovers.modules.report.dto.request.ReportRequest;
import com.company.networkmovers.modules.report.dto.response.ReportResponse;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    public ReportEntity toEntity(ReportRequest request) {
        if (request == null) return null;
        return ReportEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public ReportResponse toResponse(ReportEntity entity) {
        if (entity == null) return null;
        return ReportResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
