package com.company.networkmovers.modules.property.service.impl;

import com.company.networkmovers.modules.property.entity.PropertyCategory;
import com.company.networkmovers.modules.property.repository.PropertyCategoryRepository;
import com.company.networkmovers.modules.property.service.PropertyCategoryService;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PropertyCategoryServiceImpl 
        extends AbstractLookupService<PropertyCategory, LookupRequest, LookupResponse, PropertyCategoryRepository> 
        implements PropertyCategoryService {

    public PropertyCategoryServiceImpl(PropertyCategoryRepository repository, 
                                       GenericMapper<PropertyCategory, LookupRequest, LookupResponse> mapper) {
        super(repository, mapper);
    }

    @Override
    protected String getCodeFromRequest(LookupRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(PropertyCategory entity, LookupRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
    }
}
