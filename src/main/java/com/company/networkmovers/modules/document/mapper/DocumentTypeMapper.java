package com.company.networkmovers.modules.document.mapper;

import com.company.networkmovers.modules.document.entity.DocumentType;
import com.company.networkmovers.modules.document.dto.request.DocumentTypeRequest;
import com.company.networkmovers.modules.document.dto.response.DocumentTypeResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class DocumentTypeMapper implements GenericMapper<DocumentType, DocumentTypeRequest, DocumentTypeResponse> {

    @Override
    public DocumentType toEntity(DocumentTypeRequest request) {
        if (request == null) return null;
        return DocumentType.builder()
                .name(request.getName())
                .code(request.getCode())
                .active(request.isActive())
                .mandatory(request.isMandatory())
                .expiryRequired(request.isExpiryRequired())
                .description(request.getDescription())
                .build();
    }

    @Override
    public DocumentTypeResponse toResponse(DocumentType entity) {
        if (entity == null) return null;
        return DocumentTypeResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .active(entity.isActive())
                .mandatory(entity.isMandatory())
                .expiryRequired(entity.isExpiryRequired())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
