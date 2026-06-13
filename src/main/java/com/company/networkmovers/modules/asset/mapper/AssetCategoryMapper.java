package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.AssetCategory;
import com.company.networkmovers.modules.asset.dto.request.AssetCategoryRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetCategoryResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssetCategoryMapper implements GenericMapper<AssetCategory, AssetCategoryRequest, AssetCategoryResponse> {

    private final AssetTypeMapper assetTypeMapper;

    @Override
    public AssetCategory toEntity(AssetCategoryRequest request) {
        if (request == null) return null;
        return AssetCategory.builder()
                .name(request.getName())
                .code(request.getCode())
                .description(request.getDescription())
                .active(request.isActive())
                .build();
    }

    @Override
    public AssetCategoryResponse toResponse(AssetCategory entity) {
        if (entity == null) return null;
        return AssetCategoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .assetType(assetTypeMapper.toResponse(entity.getAssetType()))
                .parentCategory(toResponseParent(entity.getParentCategory()))
                .description(entity.getDescription())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private AssetCategoryResponse toResponseParent(AssetCategory parent) {
        if (parent == null) return null;
        return AssetCategoryResponse.builder()
                .id(parent.getId())
                .name(parent.getName())
                .code(parent.getCode())
                .active(parent.isActive())
                .build();
    }
}
