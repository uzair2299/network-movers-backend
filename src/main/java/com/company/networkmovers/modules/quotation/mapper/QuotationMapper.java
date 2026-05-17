package com.company.networkmovers.modules.quotation.mapper;

import com.company.networkmovers.modules.quotation.entity.QuotationEntity;
import com.company.networkmovers.modules.quotation.dto.request.QuotationRequest;
import com.company.networkmovers.modules.quotation.dto.response.QuotationResponse;
import org.springframework.stereotype.Component;

@Component
public class QuotationMapper {

    public QuotationEntity toEntity(QuotationRequest request) {
        if (request == null) return null;
        return QuotationEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public QuotationResponse toResponse(QuotationEntity entity) {
        if (entity == null) return null;
        return QuotationResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
