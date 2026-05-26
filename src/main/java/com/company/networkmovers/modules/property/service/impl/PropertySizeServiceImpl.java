package com.company.networkmovers.modules.property.service.impl;

import com.company.networkmovers.modules.property.entity.PropertySize;
import com.company.networkmovers.modules.property.entity.PropertyType;
import com.company.networkmovers.modules.property.repository.PropertySizeRepository;
import com.company.networkmovers.modules.property.repository.PropertyTypeRepository;
import com.company.networkmovers.modules.property.service.PropertySizeService;
import com.company.networkmovers.modules.property.dto.request.PropertySizeRequest;
import com.company.networkmovers.modules.property.dto.response.PropertySizeResponse;
import com.company.networkmovers.shared.dto.RequestParamDto;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PropertySizeServiceImpl 
        extends AbstractLookupService<PropertySize, PropertySizeRequest, PropertySizeResponse, PropertySizeRepository> 
        implements PropertySizeService {

    private final PropertyTypeRepository typeRepository;

    public PropertySizeServiceImpl(PropertySizeRepository repository, 
                                   GenericMapper<PropertySize, PropertySizeRequest, PropertySizeResponse> mapper,
                                   PropertyTypeRepository typeRepository) {
        super(repository, mapper);
        this.typeRepository = typeRepository;
    }

    @Override
    @Transactional
    public PropertySizeResponse create(PropertySizeRequest request) {
        if (request.getTypeId() == null) {
            throw new RuntimeException("PropertyType ID is required");
        }
        PropertyType type = typeRepository.findById(request.getTypeId())
                .orElseThrow(() -> new RuntimeException("PropertyType not found with ID: " + request.getTypeId()));

        if (request.getCode() != null && repository.existsByCode(request.getCode())) {
            throw new RuntimeException("Code already exists: " + request.getCode());
        }

        PropertySize entity = mapper.toEntity(request);
        entity.setType(type);
        
        PropertySize saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PropertySizeResponse> getAll(RequestParamDto requestParams) {
        Pageable pageable = createPageable(requestParams);
        return repository.searchPageWithType(requestParams.getSearch(), requestParams.getTypeId(), pageable)
                .map(mapper::toResponse);
    }

    @Override
    protected String getCodeFromRequest(PropertySizeRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(PropertySize entity, PropertySizeRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
        entity.setUnitType(request.getUnitType());
        if (request.getTypeId() != null) {
            PropertyType type = typeRepository.findById(request.getTypeId())
                    .orElseThrow(() -> new RuntimeException("PropertyType not found with ID: " + request.getTypeId()));
            entity.setType(type);
        }
    }
}
