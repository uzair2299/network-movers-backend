package com.company.networkmovers.modules.asset.service.impl;

import com.company.networkmovers.modules.asset.entity.AssetSupplier;
import com.company.networkmovers.modules.asset.dto.request.AssetSupplierRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetSupplierResponse;
import com.company.networkmovers.modules.asset.mapper.AssetSupplierMapper;
import com.company.networkmovers.modules.asset.repository.AssetSupplierRepository;
import com.company.networkmovers.modules.asset.service.AssetSupplierService;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;

@Service
public class AssetSupplierServiceImpl 
    extends AbstractLookupService<AssetSupplier, AssetSupplierRequest, AssetSupplierResponse, AssetSupplierRepository>
    implements AssetSupplierService {

    public AssetSupplierServiceImpl(AssetSupplierRepository repository, AssetSupplierMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected String getCodeFromRequest(AssetSupplierRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(AssetSupplier entity, AssetSupplierRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setContactName(request.getContactName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setAddress(request.getAddress());
        entity.setActive(request.isActive());
    }
}
