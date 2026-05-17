package com.company.networkmovers.modules.taxation.mapper;

import com.company.networkmovers.modules.taxation.entity.TaxationEntity;
import com.company.networkmovers.modules.taxation.dto.request.TaxationRequest;
import com.company.networkmovers.modules.taxation.dto.response.TaxationResponse;
import org.springframework.stereotype.Component;

@Component
public class TaxationMapper {

    public TaxationEntity toEntity(TaxationRequest request) {
        if (request == null) return null;
        return TaxationEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public TaxationResponse toResponse(TaxationEntity entity) {
        if (entity == null) return null;
        return TaxationResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
