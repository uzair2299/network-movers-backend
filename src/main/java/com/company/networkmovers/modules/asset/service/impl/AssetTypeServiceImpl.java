package com.company.networkmovers.modules.asset.service.impl;

import com.company.networkmovers.modules.asset.entity.AssetType;
import com.company.networkmovers.modules.asset.dto.request.AssetTypeRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetTypeResponse;
import com.company.networkmovers.modules.asset.mapper.AssetTypeMapper;
import com.company.networkmovers.modules.asset.repository.AssetTypeRepository;
import com.company.networkmovers.modules.asset.service.AssetTypeService;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;

@Service
public class AssetTypeServiceImpl 
    extends AbstractLookupService<AssetType, AssetTypeRequest, AssetTypeResponse, AssetTypeRepository>
    implements AssetTypeService {

    public AssetTypeServiceImpl(AssetTypeRepository repository, AssetTypeMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected String getCodeFromRequest(AssetTypeRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(AssetType entity, AssetTypeRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
    }
}
