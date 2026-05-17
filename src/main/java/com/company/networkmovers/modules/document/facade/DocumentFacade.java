package com.company.networkmovers.modules.document.facade;

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

    public DocumentResponse findById(Long id) {
        return service.findById(id);
    }

    public List<DocumentResponse> findAll() {
        return service.findAll();
    }

    public DocumentResponse update(Long id, DocumentRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
