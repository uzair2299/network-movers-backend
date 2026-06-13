package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.AssetSupplier;
import com.company.networkmovers.modules.asset.dto.request.AssetSupplierRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetSupplierResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class AssetSupplierMapper implements GenericMapper<AssetSupplier, AssetSupplierRequest, AssetSupplierResponse> {

    @Override
    public AssetSupplier toEntity(AssetSupplierRequest request) {
        if (request == null) return null;
        return AssetSupplier.builder()
                .name(request.getName())
                .code(request.getCode())
                .contactName(request.getContactName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .active(request.isActive())
                .build();
    }

    @Override
    public AssetSupplierResponse toResponse(AssetSupplier entity) {
        if (entity == null) return null;
        return AssetSupplierResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .contactName(entity.getContactName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
