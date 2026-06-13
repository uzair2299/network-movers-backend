package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.AssetLocation;
import com.company.networkmovers.modules.asset.dto.request.AssetLocationRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetLocationResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class AssetLocationMapper implements GenericMapper<AssetLocation, AssetLocationRequest, AssetLocationResponse> {

    @Override
    public AssetLocation toEntity(AssetLocationRequest request) {
        if (request == null) return null;
        return AssetLocation.builder()
                .name(request.getName())
                .code(request.getCode())
                .description(request.getDescription())
                .active(request.isActive())
                .build();
    }

    @Override
    public AssetLocationResponse toResponse(AssetLocation entity) {
        if (entity == null) return null;
        return AssetLocationResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .description(entity.getDescription())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
