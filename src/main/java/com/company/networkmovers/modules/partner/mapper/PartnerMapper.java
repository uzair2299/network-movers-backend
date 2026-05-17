package com.company.networkmovers.modules.partner.mapper;

import com.company.networkmovers.modules.partner.entity.PartnerEntity;
import com.company.networkmovers.modules.partner.dto.request.PartnerRequest;
import com.company.networkmovers.modules.partner.dto.response.PartnerResponse;
import org.springframework.stereotype.Component;

@Component
public class PartnerMapper {

    public PartnerEntity toEntity(PartnerRequest request) {
        if (request == null) return null;
        return PartnerEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public PartnerResponse toResponse(PartnerEntity entity) {
        if (entity == null) return null;
        return PartnerResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
