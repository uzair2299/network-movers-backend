package com.company.networkmovers.modules.filemanagement.mapper;

import com.company.networkmovers.modules.filemanagement.entity.FilemanagementEntity;
import com.company.networkmovers.modules.filemanagement.dto.request.FilemanagementRequest;
import com.company.networkmovers.modules.filemanagement.dto.response.FilemanagementResponse;
import org.springframework.stereotype.Component;

@Component
public class FilemanagementMapper {

    public FilemanagementEntity toEntity(FilemanagementRequest request) {
        if (request == null) return null;
        return FilemanagementEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public FilemanagementResponse toResponse(FilemanagementEntity entity) {
        if (entity == null) return null;
        return FilemanagementResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
