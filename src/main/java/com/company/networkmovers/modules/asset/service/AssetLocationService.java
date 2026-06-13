package com.company.networkmovers.modules.asset.service;

import com.company.networkmovers.modules.asset.dto.request.AssetLocationRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetLocationResponse;
import com.company.networkmovers.shared.service.GenericLookupService;

public interface AssetLocationService extends GenericLookupService<AssetLocationRequest, AssetLocationResponse> {
}
