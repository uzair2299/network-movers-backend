package com.company.networkmovers.modules.vendor.mapper;

import com.company.networkmovers.modules.vendor.entity.VendorEntity;
import com.company.networkmovers.modules.vendor.dto.request.VendorRequest;
import com.company.networkmovers.modules.vendor.dto.response.VendorResponse;
import org.springframework.stereotype.Component;

@Component
public class VendorMapper {

    public VendorEntity toEntity(VendorRequest request) {
        if (request == null) return null;
        return VendorEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public VendorResponse toResponse(VendorEntity entity) {
        if (entity == null) return null;
        return VendorResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
