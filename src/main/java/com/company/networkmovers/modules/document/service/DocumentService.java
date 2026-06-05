package com.company.networkmovers.modules.document.service;

import java.util.UUID;

import com.company.networkmovers.modules.document.dto.request.DocumentRequest;
import com.company.networkmovers.modules.document.dto.response.DocumentResponse;
import java.util.List;

public interface DocumentService {
    DocumentResponse create(DocumentRequest request);
    DocumentResponse findById(UUID id);
    List<DocumentResponse> findAll();
    DocumentResponse update(UUID id, DocumentRequest request);
    void delete(UUID id);
}
