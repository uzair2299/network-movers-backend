package com.company.networkmovers.modules.property.mapper;

import com.company.networkmovers.modules.property.entity.OccupancyType;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class OccupancyTypeMapper implements GenericMapper<OccupancyType, LookupRequest, LookupResponse> {

    @Override
    public OccupancyType toEntity(LookupRequest request) {
        if (request == null) return null;
        return OccupancyType.builder()
                .name(request.getName())
                .code(request.getCode())
                .active(request.isActive())
                .build();
    }

    @Override
    public LookupResponse toResponse(OccupancyType entity) {
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
