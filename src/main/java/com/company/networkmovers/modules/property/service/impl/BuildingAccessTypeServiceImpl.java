package com.company.networkmovers.modules.property.service.impl;

import com.company.networkmovers.modules.property.entity.BuildingAccessType;
import com.company.networkmovers.modules.property.repository.BuildingAccessTypeRepository;
import com.company.networkmovers.modules.property.service.BuildingAccessTypeService;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BuildingAccessTypeServiceImpl 
        extends AbstractLookupService<BuildingAccessType, LookupRequest, LookupResponse, BuildingAccessTypeRepository> 
        implements BuildingAccessTypeService {

    public BuildingAccessTypeServiceImpl(BuildingAccessTypeRepository repository, 
                                         GenericMapper<BuildingAccessType, LookupRequest, LookupResponse> mapper) {
        super(repository, mapper);
    }

    @Override
    protected String getCodeFromRequest(LookupRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(BuildingAccessType entity, LookupRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
    }
}
