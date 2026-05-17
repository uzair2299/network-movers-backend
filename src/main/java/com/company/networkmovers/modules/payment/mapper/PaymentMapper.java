package com.company.networkmovers.modules.payment.mapper;

import com.company.networkmovers.modules.payment.entity.PaymentEntity;
import com.company.networkmovers.modules.payment.dto.request.PaymentRequest;
import com.company.networkmovers.modules.payment.dto.response.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentEntity toEntity(PaymentRequest request) {
        if (request == null) return null;
        return PaymentEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public PaymentResponse toResponse(PaymentEntity entity) {
        if (entity == null) return null;
        return PaymentResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
