package com.company.networkmovers.modules.document.service;

import com.company.networkmovers.modules.document.dto.request.DocumentRequest;
import com.company.networkmovers.modules.document.dto.response.DocumentResponse;
import java.util.List;

public interface DocumentService {
    DocumentResponse create(DocumentRequest request);
    DocumentResponse findById(Long id);
    List<DocumentResponse> findAll();
    DocumentResponse update(Long id, DocumentRequest request);
    void delete(Long id);
}
