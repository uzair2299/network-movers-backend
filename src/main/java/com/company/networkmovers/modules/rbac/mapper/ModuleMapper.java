package com.company.networkmovers.modules.rbac.mapper;

import com.company.networkmovers.modules.rbac.entity.Module;
import com.company.networkmovers.modules.rbac.dto.request.ModuleRequest;
import com.company.networkmovers.modules.rbac.dto.response.ModuleResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component("modulesModuleMapper")
public class ModuleMapper implements GenericMapper<Module, ModuleRequest, ModuleResponse> {
    @Override
    public Module toEntity(ModuleRequest request) {
        if (request == null) return null;
        return Module.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .active(request.isActive())
                .build();
    }

    @Override
    public ModuleResponse toResponse(Module entity) {
        if (entity == null) return null;
        return ModuleResponse.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
