package com.company.networkmovers.modules.invoice.mapper;

import com.company.networkmovers.modules.invoice.entity.InvoiceEntity;
import com.company.networkmovers.modules.invoice.dto.request.InvoiceRequest;
import com.company.networkmovers.modules.invoice.dto.response.InvoiceResponse;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {

    public InvoiceEntity toEntity(InvoiceRequest request) {
        if (request == null) return null;
        return InvoiceEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public InvoiceResponse toResponse(InvoiceEntity entity) {
        if (entity == null) return null;
        return InvoiceResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
