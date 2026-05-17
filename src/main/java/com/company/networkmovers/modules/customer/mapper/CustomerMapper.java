package com.company.networkmovers.modules.customer.mapper;

import com.company.networkmovers.modules.customer.entity.CustomerEntity;
import com.company.networkmovers.modules.customer.dto.request.CustomerRequest;
import com.company.networkmovers.modules.customer.dto.response.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerEntity toEntity(CustomerRequest request) {
        if (request == null) return null;
        return CustomerEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public CustomerResponse toResponse(CustomerEntity entity) {
        if (entity == null) return null;
        return CustomerResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
