package com.company.networkmovers.modules.rbac.service.impl;

import com.company.networkmovers.modules.rbac.entity.Permission;
import com.company.networkmovers.modules.rbac.entity.Resource;
import com.company.networkmovers.modules.rbac.repository.PermissionRepository;
import com.company.networkmovers.modules.rbac.repository.ResourceRepository;
import com.company.networkmovers.modules.rbac.dto.request.PermissionRequest;
import com.company.networkmovers.modules.rbac.dto.response.PermissionResponse;
import com.company.networkmovers.modules.rbac.service.PermissionService;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("modulesPermissionServiceImpl")
@Transactional
public class PermissionServiceImpl 
        extends AbstractLookupService<Permission, PermissionRequest, PermissionResponse, PermissionRepository> 
        implements PermissionService {

    private final ResourceRepository resourceRepository;

    public PermissionServiceImpl(PermissionRepository repository,
                                 @Qualifier("modulesPermissionMapper") GenericMapper<Permission, PermissionRequest, PermissionResponse> mapper,
                                 @Qualifier("modulesResourceRepository") ResourceRepository resourceRepository) {
        super(repository, mapper);
        this.resourceRepository = resourceRepository;
    }

    @Override
    protected String getCodeFromRequest(PermissionRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(Permission entity, PermissionRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setActive(request.isActive());

        if (request.getResourceId() != null) {
            Resource resource = resourceRepository.findById(request.getResourceId()).orElse(null);
            entity.setResource(resource);
        } else {
            entity.setResource(null);
        }
    }
}
