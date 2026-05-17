package com.company.networkmovers.modules.lookup.mapper;

import com.company.networkmovers.modules.lookup.entity.LookupEntity;
import com.company.networkmovers.modules.lookup.dto.request.LookupRequest;
import com.company.networkmovers.modules.lookup.dto.response.LookupResponse;
import org.springframework.stereotype.Component;

@Component
public class LookupMapper {

    public LookupEntity toEntity(LookupRequest request) {
        if (request == null) return null;
        return LookupEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public LookupResponse toResponse(LookupEntity entity) {
        if (entity == null) return null;
        return LookupResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
