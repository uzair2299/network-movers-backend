package com.company.networkmovers.modules.contract.mapper;

import com.company.networkmovers.modules.contract.entity.ContractEntity;
import com.company.networkmovers.modules.contract.dto.request.ContractRequest;
import com.company.networkmovers.modules.contract.dto.response.ContractResponse;
import org.springframework.stereotype.Component;

@Component
public class ContractMapper {

    public ContractEntity toEntity(ContractRequest request) {
        if (request == null) return null;
        return ContractEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public ContractResponse toResponse(ContractEntity entity) {
        if (entity == null) return null;
        return ContractResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
