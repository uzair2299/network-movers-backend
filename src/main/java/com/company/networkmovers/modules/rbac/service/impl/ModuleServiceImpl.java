package com.company.networkmovers.modules.rbac.service.impl;

import com.company.networkmovers.modules.rbac.entity.Module;
import com.company.networkmovers.modules.rbac.repository.ModuleRepository;
import com.company.networkmovers.modules.rbac.dto.request.ModuleRequest;
import com.company.networkmovers.modules.rbac.dto.response.ModuleResponse;
import com.company.networkmovers.modules.rbac.service.ModuleService;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("modulesModuleServiceImpl")
@Transactional
public class ModuleServiceImpl 
        extends AbstractLookupService<Module, ModuleRequest, ModuleResponse, ModuleRepository> 
        implements ModuleService {

    public ModuleServiceImpl(ModuleRepository repository, 
                           @Qualifier("modulesModuleMapper") GenericMapper<Module, ModuleRequest, ModuleResponse> mapper) {
        super(repository, mapper);
    }

    @Override
    protected String getCodeFromRequest(ModuleRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(Module entity, ModuleRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setActive(request.isActive());
    }
}
