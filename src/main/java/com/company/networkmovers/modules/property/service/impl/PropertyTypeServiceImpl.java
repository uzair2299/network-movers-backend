package com.company.networkmovers.modules.property.service.impl;

import com.company.networkmovers.modules.property.entity.PropertyCategory;
import com.company.networkmovers.modules.property.entity.PropertyType;
import com.company.networkmovers.modules.property.repository.PropertyCategoryRepository;
import com.company.networkmovers.modules.property.repository.PropertyTypeRepository;
import com.company.networkmovers.modules.property.service.PropertyTypeService;
import com.company.networkmovers.modules.property.dto.request.PropertyTypeRequest;
import com.company.networkmovers.modules.property.dto.response.PropertyTypeResponse;
import com.company.networkmovers.shared.dto.RequestParamDto;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PropertyTypeServiceImpl 
        extends AbstractLookupService<PropertyType, PropertyTypeRequest, PropertyTypeResponse, PropertyTypeRepository> 
        implements PropertyTypeService {

    private final PropertyCategoryRepository categoryRepository;

    public PropertyTypeServiceImpl(PropertyTypeRepository repository, 
                                   GenericMapper<PropertyType, PropertyTypeRequest, PropertyTypeResponse> mapper,
                                   PropertyCategoryRepository categoryRepository) {
        super(repository, mapper);
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public PropertyTypeResponse create(PropertyTypeRequest request) {
        if (request.getCategoryId() == null) {
            throw new RuntimeException("Category ID is required");
        }
        PropertyCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("PropertyCategory not found with ID: " + request.getCategoryId()));

        if (request.getCode() != null && repository.existsByCode(request.getCode())) {
            throw new RuntimeException("Code already exists: " + request.getCode());
        }

        PropertyType entity = mapper.toEntity(request);
        entity.setCategory(category);
        
        PropertyType saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PropertyTypeResponse> getAll(RequestParamDto requestParams) {
        Pageable pageable = createPageable(requestParams);
        String search = requestParams.getSearch();
        java.util.UUID categoryId = requestParams.getCategoryId();
        
        Page<PropertyType> entityPage;
        if (categoryId == null) {
            if (search == null || search.trim().isEmpty()) {
                entityPage = repository.findAll(pageable);
            } else {
                entityPage = repository.findBySearch(search.trim(), pageable);
            }
        } else {
            if (search == null || search.trim().isEmpty()) {
                entityPage = repository.findByCategoryId(categoryId, pageable);
            } else {
                entityPage = repository.findByCategoryIdAndSearch(categoryId, search.trim(), pageable);
            }
        }
        return entityPage.map(mapper::toResponse);
    }

    @Override
    protected String getCodeFromRequest(PropertyTypeRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(PropertyType entity, PropertyTypeRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
        if (request.getCategoryId() != null) {
            PropertyCategory category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("PropertyCategory not found with ID: " + request.getCategoryId()));
            entity.setCategory(category);
        }
    }
}
