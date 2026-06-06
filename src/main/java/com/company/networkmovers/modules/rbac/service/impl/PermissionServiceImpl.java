package com.company.networkmovers.modules.rbac.service.impl;

import com.company.networkmovers.modules.rbac.entity.Permission;
import com.company.networkmovers.modules.rbac.repository.PermissionRepository;
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

    public PermissionServiceImpl(PermissionRepository repository, 
                                 @Qualifier("modulesPermissionMapper") GenericMapper<Permission, PermissionRequest, PermissionResponse> mapper) {
        super(repository, mapper);
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
    }
}
