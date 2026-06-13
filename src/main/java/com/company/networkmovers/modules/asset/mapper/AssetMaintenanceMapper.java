package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.AssetMaintenance;
import com.company.networkmovers.modules.asset.dto.request.AssetMaintenanceRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetMaintenanceResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssetMaintenanceMapper implements GenericMapper<AssetMaintenance, AssetMaintenanceRequest, AssetMaintenanceResponse> {

    private final AssetMapper assetMapper;

    @Override
    public AssetMaintenance toEntity(AssetMaintenanceRequest request) {
        if (request == null) return null;
        return AssetMaintenance.builder()
                .maintenanceType(request.getMaintenanceType())
                .description(request.getDescription())
                .scheduledDate(request.getScheduledDate())
                .startDate(request.getStartDate())
                .completionDate(request.getCompletionDate())
                .cost(request.getCost())
                .performedBy(request.getPerformedBy())
                .remarks(request.getRemarks())
                .status(request.getStatus())
                .build();
    }

    @Override
    public AssetMaintenanceResponse toResponse(AssetMaintenance entity) {
        if (entity == null) return null;
        return AssetMaintenanceResponse.builder()
                .id(entity.getId())
                .asset(assetMapper.toResponse(entity.getAsset()))
                .maintenanceType(entity.getMaintenanceType())
                .description(entity.getDescription())
                .scheduledDate(entity.getScheduledDate())
                .startDate(entity.getStartDate())
                .completionDate(entity.getCompletionDate())
                .cost(entity.getCost())
                .performedBy(entity.getPerformedBy())
                .remarks(entity.getRemarks())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
