package com.company.networkmovers.modules.document.mapper;

import com.company.networkmovers.modules.document.entity.DocumentEntity;
import com.company.networkmovers.modules.document.dto.request.DocumentRequest;
import com.company.networkmovers.modules.document.dto.response.DocumentResponse;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {

    public DocumentEntity toEntity(DocumentRequest request) {
        if (request == null) return null;
        return DocumentEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public DocumentResponse toResponse(DocumentEntity entity) {
        if (entity == null) return null;
        return DocumentResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
