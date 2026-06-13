package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.StockAudit;
import com.company.networkmovers.modules.asset.dto.request.StockAuditRequest;
import com.company.networkmovers.modules.asset.dto.response.StockAuditResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StockAuditMapper implements GenericMapper<StockAudit, StockAuditRequest, StockAuditResponse> {

    private final StockAuditItemMapper itemMapper;

    @Override
    public StockAudit toEntity(StockAuditRequest request) {
        if (request == null) return null;
        return StockAudit.builder()
                .auditDate(request.getAuditDate())
                .auditorId(request.getAuditorId())
                .remarks(request.getRemarks())
                .status(request.getStatus())
                .build();
    }

    @Override
    public StockAuditResponse toResponse(StockAudit entity) {
        if (entity == null) return null;
        return StockAuditResponse.builder()
                .id(entity.getId())
                .auditDate(entity.getAuditDate())
                .auditorId(entity.getAuditorId())
                .remarks(entity.getRemarks())
                .status(entity.getStatus())
                .items(entity.getItems() == null ? new ArrayList<>() : entity.getItems().stream()
                        .map(itemMapper::toResponse)
                        .collect(Collectors.toList()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
