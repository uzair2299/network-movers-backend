package com.company.networkmovers.modules.property.mapper;

import com.company.networkmovers.modules.property.entity.ParkingAccessType;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class ParkingAccessTypeMapper implements GenericMapper<ParkingAccessType, LookupRequest, LookupResponse> {

    @Override
    public ParkingAccessType toEntity(LookupRequest request) {
        if (request == null) return null;
        return ParkingAccessType.builder()
                .name(request.getName())
                .code(request.getCode())
                .active(request.isActive())
                .build();
    }

    @Override
    public LookupResponse toResponse(ParkingAccessType entity) {
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
