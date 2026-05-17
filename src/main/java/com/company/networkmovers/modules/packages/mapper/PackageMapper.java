package com.company.networkmovers.modules.packages.mapper;

import com.company.networkmovers.modules.packages.entity.PackageEntity;
import com.company.networkmovers.modules.packages.dto.request.PackageRequest;
import com.company.networkmovers.modules.packages.dto.response.PackageResponse;
import org.springframework.stereotype.Component;

@Component
public class PackageMapper {

    public PackageEntity toEntity(PackageRequest request) {
        if (request == null) return null;
        return PackageEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public PackageResponse toResponse(PackageEntity entity) {
        if (entity == null) return null;
        return PackageResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
