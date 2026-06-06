package com.company.networkmovers.modules.rbac.service.impl;

import com.company.networkmovers.modules.rbac.entity.Role;
import com.company.networkmovers.modules.rbac.repository.RoleRepository;
import com.company.networkmovers.modules.rbac.dto.request.RoleRequest;
import com.company.networkmovers.modules.rbac.dto.response.RoleResponse;
import com.company.networkmovers.modules.rbac.service.RoleService;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("modulesRoleServiceImpl")
@Transactional
public class RoleServiceImpl 
        extends AbstractLookupService<Role, RoleRequest, RoleResponse, RoleRepository> 
        implements RoleService {

    public RoleServiceImpl(RoleRepository repository, 
                           @Qualifier("modulesRoleMapper") GenericMapper<Role, RoleRequest, RoleResponse> mapper) {
        super(repository, mapper);
    }

    @Override
    protected String getCodeFromRequest(RoleRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(Role entity, RoleRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setActive(request.isActive());
    }
}
