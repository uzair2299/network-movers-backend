package com.company.networkmovers.modules.property.service.impl;

import com.company.networkmovers.modules.property.entity.AccessRestrictionType;
import com.company.networkmovers.modules.property.repository.AccessRestrictionTypeRepository;
import com.company.networkmovers.modules.property.service.AccessRestrictionTypeService;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessRestrictionTypeServiceImpl 
        extends AbstractLookupService<AccessRestrictionType, LookupRequest, LookupResponse, AccessRestrictionTypeRepository> 
        implements AccessRestrictionTypeService {

    public AccessRestrictionTypeServiceImpl(AccessRestrictionTypeRepository repository, 
                                            GenericMapper<AccessRestrictionType, LookupRequest, LookupResponse> mapper) {
        super(repository, mapper);
    }

    @Override
    protected String getCodeFromRequest(LookupRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(AccessRestrictionType entity, LookupRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
    }
}
