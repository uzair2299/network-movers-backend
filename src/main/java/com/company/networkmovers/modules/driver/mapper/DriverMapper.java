package com.company.networkmovers.modules.driver.mapper;

import com.company.networkmovers.modules.driver.entity.DriverEntity;
import com.company.networkmovers.modules.driver.dto.request.DriverRequest;
import com.company.networkmovers.modules.driver.dto.response.DriverResponse;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {

    public DriverEntity toEntity(DriverRequest request) {
        if (request == null) return null;
        return DriverEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public DriverResponse toResponse(DriverEntity entity) {
        if (entity == null) return null;
        return DriverResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
