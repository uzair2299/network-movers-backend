package com.company.networkmovers.security.rbac.service.impl;

import com.company.networkmovers.security.rbac.Role;
import com.company.networkmovers.security.rbac.RoleRepository;
import com.company.networkmovers.security.rbac.dto.request.RoleRequest;
import com.company.networkmovers.security.rbac.dto.response.RoleResponse;
import com.company.networkmovers.security.rbac.service.RoleService;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl 
        extends AbstractLookupService<Role, RoleRequest, RoleResponse, RoleRepository> 
        implements RoleService {

    public RoleServiceImpl(RoleRepository repository, 
                                GenericMapper<Role, RoleRequest, RoleResponse> mapper) {
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
