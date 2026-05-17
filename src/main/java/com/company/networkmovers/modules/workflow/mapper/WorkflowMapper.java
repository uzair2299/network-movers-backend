package com.company.networkmovers.modules.workflow.mapper;

import com.company.networkmovers.modules.workflow.entity.WorkflowEntity;
import com.company.networkmovers.modules.workflow.dto.request.WorkflowRequest;
import com.company.networkmovers.modules.workflow.dto.response.WorkflowResponse;
import org.springframework.stereotype.Component;

@Component
public class WorkflowMapper {

    public WorkflowEntity toEntity(WorkflowRequest request) {
        if (request == null) return null;
        return WorkflowEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public WorkflowResponse toResponse(WorkflowEntity entity) {
        if (entity == null) return null;
        return WorkflowResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
