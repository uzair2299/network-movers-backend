package com.company.networkmovers.modules.property.mapper;

import com.company.networkmovers.modules.property.entity.BuildingAccessType;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class BuildingAccessTypeMapper implements GenericMapper<BuildingAccessType, LookupRequest, LookupResponse> {

    @Override
    public BuildingAccessType toEntity(LookupRequest request) {
        if (request == null) return null;
        return BuildingAccessType.builder()
                .name(request.getName())
                .code(request.getCode())
                .active(request.isActive())
                .build();
    }

    @Override
    public LookupResponse toResponse(BuildingAccessType entity) {
        if (entity == null) return null;
        return LookupResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
