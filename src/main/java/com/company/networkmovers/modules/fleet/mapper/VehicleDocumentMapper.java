package com.company.networkmovers.modules.fleet.mapper;

import com.company.networkmovers.modules.fleet.entity.VehicleDocument;
import com.company.networkmovers.modules.fleet.dto.request.VehicleDocumentRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleDocumentResponse;
import com.company.networkmovers.modules.document.mapper.DocumentTypeMapper;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleDocumentMapper implements GenericMapper<VehicleDocument, VehicleDocumentRequest, VehicleDocumentResponse> {

    private final DocumentTypeMapper documentTypeMapper;

    @Override
    public VehicleDocument toEntity(VehicleDocumentRequest request) {
        if (request == null) return null;
        return VehicleDocument.builder()
                .documentNumber(request.getDocumentNumber())
                .issueDate(request.getIssueDate())
                .expiryDate(request.getExpiryDate())
                .fileUrl(request.getFileUrl())
                .verified(request.isVerified())
                .remarks(request.getRemarks())
                .build();
    }

    @Override
    public VehicleDocumentResponse toResponse(VehicleDocument entity) {
        if (entity == null) return null;
        return VehicleDocumentResponse.builder()
                .id(entity.getId())
                .vehicleId(entity.getVehicle().getId())
                .documentNumber(entity.getDocumentNumber())
                .issueDate(entity.getIssueDate())
                .expiryDate(entity.getExpiryDate())
                .fileUrl(entity.getFileUrl())
                .verified(entity.isVerified())
                .remarks(entity.getRemarks())
                .documentType(documentTypeMapper.toResponse(entity.getDocumentType()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
