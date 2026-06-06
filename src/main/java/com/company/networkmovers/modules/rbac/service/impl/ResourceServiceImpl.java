package com.company.networkmovers.modules.rbac.service.impl;

import com.company.networkmovers.modules.rbac.entity.Resource;
import com.company.networkmovers.modules.rbac.repository.ResourceRepository;
import com.company.networkmovers.modules.rbac.repository.ModuleRepository;
import com.company.networkmovers.modules.rbac.dto.request.ResourceRequest;
import com.company.networkmovers.modules.rbac.dto.response.ResourceResponse;
import com.company.networkmovers.modules.rbac.service.ResourceService;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("modulesResourceServiceImpl")
@Transactional
public class ResourceServiceImpl 
        extends AbstractLookupService<Resource, ResourceRequest, ResourceResponse, ResourceRepository> 
        implements ResourceService {

    private final ModuleRepository moduleRepository;

    public ResourceServiceImpl(ResourceRepository repository, 
                               @Qualifier("modulesResourceMapper") GenericMapper<Resource, ResourceRequest, ResourceResponse> mapper,
                               ModuleRepository moduleRepository) {
        super(repository, mapper);
        this.moduleRepository = moduleRepository;
    }

    @Override
    protected String getCodeFromRequest(ResourceRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(Resource entity, ResourceRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setActive(request.isActive());
        
        if (request.getModuleId() != null) {
            moduleRepository.findById(request.getModuleId())
                    .ifPresentOrElse(entity::setModule, () -> entity.setModule(null));
        } else {
            entity.setModule(null);
        }
    }
}
