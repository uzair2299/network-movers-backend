package com.company.networkmovers.modules.rbac.mapper;

import com.company.networkmovers.modules.rbac.entity.Resource;
import com.company.networkmovers.modules.rbac.repository.ModuleRepository;
import com.company.networkmovers.modules.rbac.dto.request.ResourceRequest;
import com.company.networkmovers.modules.rbac.dto.response.ResourceResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component("modulesResourceMapper")
public class ResourceMapper implements GenericMapper<Resource, ResourceRequest, ResourceResponse> {

    private final ModuleRepository moduleRepository;

    public ResourceMapper(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    @Override
    public Resource toEntity(ResourceRequest request) {
        if (request == null) return null;
        Resource.ResourceBuilder<?, ?> builder = Resource.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .active(request.isActive());
        
        if (request.getModuleId() != null) {
            moduleRepository.findById(request.getModuleId())
                    .ifPresent(builder::module);
        }
        
        return builder.build();
    }

    @Override
    public ResourceResponse toResponse(Resource entity) {
        if (entity == null) return null;
        ResourceResponse.ResourceResponseBuilder builder = ResourceResponse.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt());

        if (entity.getModule() != null) {
            builder.moduleId(entity.getModule().getId());
            builder.moduleName(entity.getModule().getName());
        }

        return builder.build();
    }
}
