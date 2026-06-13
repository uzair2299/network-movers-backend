package com.company.networkmovers.modules.asset.service;

import com.company.networkmovers.modules.asset.dto.request.AssetMaintenanceRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetMaintenanceResponse;
import com.company.networkmovers.shared.dto.RequestParamDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AssetMaintenanceService {
    AssetMaintenanceResponse create(AssetMaintenanceRequest request, UUID locationId);
    AssetMaintenanceResponse update(UUID id, AssetMaintenanceRequest request);
    AssetMaintenanceResponse getById(UUID id);
    Page<AssetMaintenanceResponse> getAll(RequestParamDto requestParams);
    AssetMaintenanceResponse updateStatus(UUID id, String status, UUID locationId);
    void delete(UUID id);
}
