package com.company.networkmovers.modules.asset.service.impl;

import com.company.networkmovers.modules.asset.entity.AssetCompany;
import com.company.networkmovers.modules.asset.dto.request.AssetCompanyRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetCompanyResponse;
import com.company.networkmovers.modules.asset.mapper.AssetCompanyMapper;
import com.company.networkmovers.modules.asset.repository.AssetCompanyRepository;
import com.company.networkmovers.modules.asset.service.AssetCompanyService;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;

@Service
public class AssetCompanyServiceImpl 
    extends AbstractLookupService<AssetCompany, AssetCompanyRequest, AssetCompanyResponse, AssetCompanyRepository>
    implements AssetCompanyService {

    public AssetCompanyServiceImpl(AssetCompanyRepository repository, AssetCompanyMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected String getCodeFromRequest(AssetCompanyRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(AssetCompany entity, AssetCompanyRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
    }
}
