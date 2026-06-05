package com.company.networkmovers.modules.document.facade;

import java.util.UUID;

import com.company.networkmovers.modules.document.dto.request.DocumentRequest;
import com.company.networkmovers.modules.document.dto.response.DocumentResponse;
import com.company.networkmovers.modules.document.service.DocumentService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DocumentFacade {

    private final DocumentService service;

    public DocumentFacade(DocumentService service) {
        this.service = service;
    }

    public DocumentResponse create(DocumentRequest request) {
        return service.create(request);
    }

    public DocumentResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<DocumentResponse> findAll() {
        return service.findAll();
    }

    public DocumentResponse update(UUID id, DocumentRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
