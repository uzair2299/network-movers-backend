package com.company.networkmovers.modules.document.service;

import com.company.networkmovers.modules.document.dto.request.DocumentTypeRequest;
import com.company.networkmovers.modules.document.dto.response.DocumentTypeResponse;
import com.company.networkmovers.modules.document.entity.DocumentType;
import com.company.networkmovers.shared.service.GenericLookupService;

public interface DocumentTypeService extends GenericLookupService<DocumentTypeRequest, DocumentTypeResponse> {
}
