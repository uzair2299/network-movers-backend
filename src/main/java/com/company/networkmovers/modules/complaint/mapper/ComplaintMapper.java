package com.company.networkmovers.modules.complaint.mapper;

import com.company.networkmovers.modules.complaint.entity.ComplaintEntity;
import com.company.networkmovers.modules.complaint.dto.request.ComplaintRequest;
import com.company.networkmovers.modules.complaint.dto.response.ComplaintResponse;
import org.springframework.stereotype.Component;

@Component
public class ComplaintMapper {

    public ComplaintEntity toEntity(ComplaintRequest request) {
        if (request == null) return null;
        return ComplaintEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public ComplaintResponse toResponse(ComplaintEntity entity) {
        if (entity == null) return null;
        return ComplaintResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
