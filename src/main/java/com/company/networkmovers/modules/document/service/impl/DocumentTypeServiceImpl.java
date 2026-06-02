package com.company.networkmovers.modules.document.service.impl;

import com.company.networkmovers.modules.document.dto.request.DocumentTypeRequest;
import com.company.networkmovers.modules.document.dto.response.DocumentTypeResponse;
import com.company.networkmovers.modules.document.entity.DocumentType;
import com.company.networkmovers.modules.document.mapper.DocumentTypeMapper;
import com.company.networkmovers.modules.document.repository.DocumentTypeRepository;
import com.company.networkmovers.modules.document.service.DocumentTypeService;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;

@Service
public class DocumentTypeServiceImpl 
    extends AbstractLookupService<DocumentType, DocumentTypeRequest, DocumentTypeResponse, DocumentTypeRepository> 
    implements DocumentTypeService {

    private final DocumentTypeRepository repository;
    private final DocumentTypeMapper mapper;

    public DocumentTypeServiceImpl(DocumentTypeRepository repository, DocumentTypeMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }
@Override
    protected String getCodeFromRequest(com.company.networkmovers.modules.document.dto.request.DocumentTypeRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(DocumentType entity, DocumentTypeRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
        entity.setMandatory(request.isMandatory());
        entity.setExpiryRequired(request.isExpiryRequired());
        entity.setDescription(request.getDescription());
    }
}
