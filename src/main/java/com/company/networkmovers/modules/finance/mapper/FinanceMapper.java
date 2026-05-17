package com.company.networkmovers.modules.finance.mapper;

import com.company.networkmovers.modules.finance.entity.FinanceEntity;
import com.company.networkmovers.modules.finance.dto.request.FinanceRequest;
import com.company.networkmovers.modules.finance.dto.response.FinanceResponse;
import org.springframework.stereotype.Component;

@Component
public class FinanceMapper {

    public FinanceEntity toEntity(FinanceRequest request) {
        if (request == null) return null;
        return FinanceEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public FinanceResponse toResponse(FinanceEntity entity) {
        if (entity == null) return null;
        return FinanceResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
