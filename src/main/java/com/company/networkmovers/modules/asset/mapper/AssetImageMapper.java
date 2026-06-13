package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.AssetImage;
import com.company.networkmovers.modules.asset.dto.response.AssetImageResponse;
import org.springframework.stereotype.Component;

@Component
public class AssetImageMapper {

    public AssetImageResponse toResponse(AssetImage entity) {
        if (entity == null) return null;
        return AssetImageResponse.builder()
                .id(entity.getId())
                .imageName(entity.getImageName())
                .imagePath(entity.getImagePath())
                .isPrimary(entity.isPrimary())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
