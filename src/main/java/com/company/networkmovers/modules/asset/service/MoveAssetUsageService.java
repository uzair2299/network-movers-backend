package com.company.networkmovers.modules.asset.service;

import com.company.networkmovers.modules.asset.dto.request.MoveAssetUsageRequest;
import com.company.networkmovers.modules.asset.dto.response.MoveAssetUsageResponse;
import com.company.networkmovers.shared.dto.RequestParamDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface MoveAssetUsageService {
    MoveAssetUsageResponse create(MoveAssetUsageRequest request);
    MoveAssetUsageResponse update(UUID id, MoveAssetUsageRequest request);
    MoveAssetUsageResponse getById(UUID id);
    Page<MoveAssetUsageResponse> getAll(RequestParamDto requestParams);
    MoveAssetUsageResponse updateStatus(UUID id, String status, UUID locationId);
    void delete(UUID id);
}
