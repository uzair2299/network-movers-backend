package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.DamageReport;
import com.company.networkmovers.modules.asset.dto.request.DamageReportRequest;
import com.company.networkmovers.modules.asset.dto.response.DamageReportResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DamageReportMapper implements GenericMapper<DamageReport, DamageReportRequest, DamageReportResponse> {

    private final AssetMapper assetMapper;

    @Override
    public DamageReport toEntity(DamageReportRequest request) {
        if (request == null) return null;
        return DamageReport.builder()
                .reportedBy(request.getReportedBy())
                .damageDate(request.getDamageDate())
                .description(request.getDescription())
                .severity(request.getSeverity())
                .status(request.getStatus())
                .actionTaken(request.getActionTaken())
                .build();
    }

    @Override
    public DamageReportResponse toResponse(DamageReport entity) {
        if (entity == null) return null;
        return DamageReportResponse.builder()
                .id(entity.getId())
                .asset(assetMapper.toResponse(entity.getAsset()))
                .reportedBy(entity.getReportedBy())
                .damageDate(entity.getDamageDate())
                .description(entity.getDescription())
                .severity(entity.getSeverity())
                .status(entity.getStatus())
                .actionTaken(entity.getActionTaken())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
