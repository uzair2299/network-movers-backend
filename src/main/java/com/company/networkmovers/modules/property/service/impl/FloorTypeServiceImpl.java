package com.company.networkmovers.modules.property.service.impl;

import com.company.networkmovers.modules.property.entity.FloorType;
import com.company.networkmovers.modules.property.repository.FloorTypeRepository;
import com.company.networkmovers.modules.property.service.FloorTypeService;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FloorTypeServiceImpl 
        extends AbstractLookupService<FloorType, LookupRequest, LookupResponse, FloorTypeRepository> 
        implements FloorTypeService {

    public FloorTypeServiceImpl(FloorTypeRepository repository, 
                                GenericMapper<FloorType, LookupRequest, LookupResponse> mapper) {
        super(repository, mapper);
    }

    @Override
    protected String getCodeFromRequest(LookupRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(FloorType entity, LookupRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
    }
}
