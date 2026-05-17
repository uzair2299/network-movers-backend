package com.company.networkmovers.modules.fraud.mapper;

import com.company.networkmovers.modules.fraud.entity.FraudEntity;
import com.company.networkmovers.modules.fraud.dto.request.FraudRequest;
import com.company.networkmovers.modules.fraud.dto.response.FraudResponse;
import org.springframework.stereotype.Component;

@Component
public class FraudMapper {

    public FraudEntity toEntity(FraudRequest request) {
        if (request == null) return null;
        return FraudEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public FraudResponse toResponse(FraudEntity entity) {
        if (entity == null) return null;
        return FraudResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
