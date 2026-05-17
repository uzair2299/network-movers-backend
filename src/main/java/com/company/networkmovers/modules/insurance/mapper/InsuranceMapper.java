package com.company.networkmovers.modules.insurance.mapper;

import com.company.networkmovers.modules.insurance.entity.InsuranceEntity;
import com.company.networkmovers.modules.insurance.dto.request.InsuranceRequest;
import com.company.networkmovers.modules.insurance.dto.response.InsuranceResponse;
import org.springframework.stereotype.Component;

@Component
public class InsuranceMapper {

    public InsuranceEntity toEntity(InsuranceRequest request) {
        if (request == null) return null;
        return InsuranceEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public InsuranceResponse toResponse(InsuranceEntity entity) {
        if (entity == null) return null;
        return InsuranceResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
