package com.company.networkmovers.modules.accounting.mapper;

import com.company.networkmovers.modules.accounting.entity.AccountingEntity;
import com.company.networkmovers.modules.accounting.dto.request.AccountingRequest;
import com.company.networkmovers.modules.accounting.dto.response.AccountingResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountingMapper {

    public AccountingEntity toEntity(AccountingRequest request) {
        if (request == null) return null;
        return AccountingEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public AccountingResponse toResponse(AccountingEntity entity) {
        if (entity == null) return null;
        return AccountingResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
