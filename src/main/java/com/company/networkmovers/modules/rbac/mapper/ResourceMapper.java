package com.company.networkmovers.modules.rbac.mapper;

import com.company.networkmovers.modules.rbac.entity.Resource;
import com.company.networkmovers.modules.rbac.dto.request.ResourceRequest;
import com.company.networkmovers.modules.rbac.dto.response.ResourceResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component("modulesResourceMapper")
public class ResourceMapper implements GenericMapper<Resource, ResourceRequest, ResourceResponse> {

    @Override
    public Resource toEntity(ResourceRequest request) {
        if (request == null) return null;
        return Resource.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .active(request.isActive())
                .build();
    }

    @Override
    public ResourceResponse toResponse(Resource entity) {
        if (entity == null) return null;
        return ResourceResponse.builder()
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
