package com.company.networkmovers.modules.property.mapper;

import com.company.networkmovers.modules.property.entity.PropertySize;
import com.company.networkmovers.modules.property.entity.PropertyType;
import com.company.networkmovers.modules.property.dto.request.PropertySizeRequest;
import com.company.networkmovers.modules.property.dto.response.PropertySizeResponse;
import com.company.networkmovers.modules.property.dto.response.PropertyTypeResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class PropertySizeMapper implements GenericMapper<PropertySize, PropertySizeRequest, PropertySizeResponse> {

    private final PropertyTypeMapper typeMapper;

    public PropertySizeMapper(PropertyTypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    @Override
    public PropertySize toEntity(PropertySizeRequest request) {
        if (request == null) return null;
        PropertyType type = null;
        if (request.getTypeId() != null) {
            type = PropertyType.builder().id(request.getTypeId()).build();
        }
        return PropertySize.builder()
                .name(request.getName())
                .code(request.getCode())
                .active(request.isActive())
                .unitType(request.getUnitType())
                .type(type)
                .build();
    }

    @Override
    public PropertySizeResponse toResponse(PropertySize entity) {
        if (entity == null) return null;
        PropertyTypeResponse typeResponse = typeMapper.toResponse(entity.getType());
        return PropertySizeResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .active(entity.isActive())
                .type(typeResponse)
                .unitType(entity.getUnitType())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
