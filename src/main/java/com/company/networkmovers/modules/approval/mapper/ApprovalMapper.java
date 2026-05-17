package com.company.networkmovers.modules.approval.mapper;

import com.company.networkmovers.modules.approval.entity.ApprovalEntity;
import com.company.networkmovers.modules.approval.dto.request.ApprovalRequest;
import com.company.networkmovers.modules.approval.dto.response.ApprovalResponse;
import org.springframework.stereotype.Component;

@Component
public class ApprovalMapper {

    public ApprovalEntity toEntity(ApprovalRequest request) {
        if (request == null) return null;
        return ApprovalEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public ApprovalResponse toResponse(ApprovalEntity entity) {
        if (entity == null) return null;
        return ApprovalResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
