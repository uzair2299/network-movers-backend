package com.company.networkmovers.modules.property.service.impl;

import com.company.networkmovers.modules.property.entity.OccupancyType;
import com.company.networkmovers.modules.property.repository.OccupancyTypeRepository;
import com.company.networkmovers.modules.property.service.OccupancyTypeService;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OccupancyTypeServiceImpl 
        extends AbstractLookupService<OccupancyType, LookupRequest, LookupResponse, OccupancyTypeRepository> 
        implements OccupancyTypeService {

    public OccupancyTypeServiceImpl(OccupancyTypeRepository repository, 
                                    GenericMapper<OccupancyType, LookupRequest, LookupResponse> mapper) {
        super(repository, mapper);
    }

    @Override
    protected String getCodeFromRequest(LookupRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(OccupancyType entity, LookupRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
    }
}
