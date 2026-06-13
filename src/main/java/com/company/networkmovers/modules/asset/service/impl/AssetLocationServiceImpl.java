package com.company.networkmovers.modules.asset.service.impl;

import com.company.networkmovers.modules.asset.entity.AssetLocation;
import com.company.networkmovers.modules.asset.dto.request.AssetLocationRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetLocationResponse;
import com.company.networkmovers.modules.asset.mapper.AssetLocationMapper;
import com.company.networkmovers.modules.asset.repository.AssetLocationRepository;
import com.company.networkmovers.modules.asset.service.AssetLocationService;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;

@Service
public class AssetLocationServiceImpl 
    extends AbstractLookupService<AssetLocation, AssetLocationRequest, AssetLocationResponse, AssetLocationRepository>
    implements AssetLocationService {

    public AssetLocationServiceImpl(AssetLocationRepository repository, AssetLocationMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected String getCodeFromRequest(AssetLocationRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(AssetLocation entity, AssetLocationRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setActive(request.isActive());
    }
}
