package com.company.networkmovers.modules.payroll.mapper;

import com.company.networkmovers.modules.payroll.entity.PayrollEntity;
import com.company.networkmovers.modules.payroll.dto.request.PayrollRequest;
import com.company.networkmovers.modules.payroll.dto.response.PayrollResponse;
import org.springframework.stereotype.Component;

@Component
public class PayrollMapper {

    public PayrollEntity toEntity(PayrollRequest request) {
        if (request == null) return null;
        return PayrollEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public PayrollResponse toResponse(PayrollEntity entity) {
        if (entity == null) return null;
        return PayrollResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
