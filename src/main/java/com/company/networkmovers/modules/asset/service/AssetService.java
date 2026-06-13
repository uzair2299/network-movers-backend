package com.company.networkmovers.modules.asset.service;

import com.company.networkmovers.modules.asset.dto.request.AssetRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetResponse;
import com.company.networkmovers.shared.dto.RequestParamDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AssetService {
    AssetResponse create(AssetRequest request);
    AssetResponse update(UUID id, AssetRequest request);
    AssetResponse getById(UUID id);
    Page<AssetResponse> getAll(RequestParamDto requestParams);
    AssetResponse updateStatus(UUID id, String status);
    void delete(UUID id);
}
