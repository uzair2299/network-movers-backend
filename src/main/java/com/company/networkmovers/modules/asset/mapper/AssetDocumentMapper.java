package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.AssetDocument;
import com.company.networkmovers.modules.asset.dto.response.AssetDocumentResponse;
import org.springframework.stereotype.Component;

@Component
public class AssetDocumentMapper {

    public AssetDocumentResponse toResponse(AssetDocument entity) {
        if (entity == null) return null;
        return AssetDocumentResponse.builder()
                .id(entity.getId())
                .documentName(entity.getDocumentName())
                .documentType(entity.getDocumentType())
                .filePath(entity.getFilePath())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
