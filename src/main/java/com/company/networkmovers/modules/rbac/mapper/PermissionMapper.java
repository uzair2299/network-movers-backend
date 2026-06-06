package com.company.networkmovers.modules.rbac.mapper;

import com.company.networkmovers.modules.rbac.entity.Permission;
import com.company.networkmovers.modules.rbac.entity.Resource;
import com.company.networkmovers.modules.rbac.dto.request.PermissionRequest;
import com.company.networkmovers.modules.rbac.dto.response.PermissionResponse;
import com.company.networkmovers.modules.rbac.repository.ResourceRepository;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component("modulesPermissionMapper")
public class PermissionMapper implements GenericMapper<Permission, PermissionRequest, PermissionResponse> {

    private final ResourceRepository resourceRepository;

    public PermissionMapper(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Permission toEntity(PermissionRequest request) {
        if (request == null) return null;

        Resource resource = (request.getResourceId() != null)
                ? resourceRepository.findById(request.getResourceId()).orElse(null)
                : null;

        return Permission.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .resource(resource)
                .active(request.isActive())
                .build();
    }

    @Override
    public PermissionResponse toResponse(Permission entity) {
        if (entity == null) return null;

        Resource resource = entity.getResource();

        return PermissionResponse.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .resourceId(resource != null ? resource.getId() : null)
                .resourceName(resource != null ? resource.getName() : null)
                .resourceCode(resource != null ? resource.getCode() : null)
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
