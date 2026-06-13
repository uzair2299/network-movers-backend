package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.AssetType;
import com.company.networkmovers.modules.asset.dto.request.AssetTypeRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetTypeResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class AssetTypeMapper implements GenericMapper<AssetType, AssetTypeRequest, AssetTypeResponse> {

    @Override
    public AssetType toEntity(AssetTypeRequest request) {
        if (request == null) return null;
        return AssetType.builder()
                .name(request.getName())
                .code(request.getCode())
                .active(request.isActive())
                .build();
    }

    @Override
    public AssetTypeResponse toResponse(AssetType entity) {
        if (entity == null) return null;
        return AssetTypeResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
