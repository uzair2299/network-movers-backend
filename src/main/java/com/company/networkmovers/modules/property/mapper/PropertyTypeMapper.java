package com.company.networkmovers.modules.property.mapper;

import com.company.networkmovers.modules.property.entity.PropertyCategory;
import com.company.networkmovers.modules.property.entity.PropertyType;
import com.company.networkmovers.modules.property.dto.request.PropertyTypeRequest;
import com.company.networkmovers.modules.property.dto.response.PropertyTypeResponse;
import com.company.networkmovers.shared.dto.LookupResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class PropertyTypeMapper implements GenericMapper<PropertyType, PropertyTypeRequest, PropertyTypeResponse> {

    @Override
    public PropertyType toEntity(PropertyTypeRequest request) {
        if (request == null) return null;
        PropertyCategory category = null;
        if (request.getCategoryId() != null) {
            category = PropertyCategory.builder().id(request.getCategoryId()).build();
        }
        return PropertyType.builder()
                .name(request.getName())
                .code(request.getCode())
                .active(request.isActive())
                .category(category)
                .build();
    }

    @Override
    public PropertyTypeResponse toResponse(PropertyType entity) {
        if (entity == null) return null;
        LookupResponse categoryResponse = null;
        if (entity.getCategory() != null) {
            categoryResponse = LookupResponse.builder()
                    .id(entity.getCategory().getId())
                    .name(entity.getCategory().getName())
                    .code(entity.getCategory().getCode())
                    .active(entity.getCategory().isActive())
                    .createdAt(entity.getCategory().getCreatedAt())
                    .updatedAt(entity.getCategory().getUpdatedAt())
                    .build();
        }
        return PropertyTypeResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .active(entity.isActive())
                .category(categoryResponse)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
