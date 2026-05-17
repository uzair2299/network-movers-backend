package com.company.networkmovers.modules.backup.mapper;

import com.company.networkmovers.modules.backup.entity.BackupEntity;
import com.company.networkmovers.modules.backup.dto.request.BackupRequest;
import com.company.networkmovers.modules.backup.dto.response.BackupResponse;
import org.springframework.stereotype.Component;

@Component
public class BackupMapper {

    public BackupEntity toEntity(BackupRequest request) {
        if (request == null) return null;
        return BackupEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public BackupResponse toResponse(BackupEntity entity) {
        if (entity == null) return null;
        return BackupResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
