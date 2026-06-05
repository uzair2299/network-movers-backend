package com.company.networkmovers.modules.audit.facade;

import java.util.UUID;

import com.company.networkmovers.modules.audit.dto.request.AuditRequest;
import com.company.networkmovers.modules.audit.dto.response.AuditResponse;
import com.company.networkmovers.modules.audit.service.AuditService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AuditFacade {

    private final AuditService service;

    public AuditFacade(AuditService service) {
        this.service = service;
    }

    public AuditResponse create(AuditRequest request) {
        return service.create(request);
    }

    public AuditResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<AuditResponse> findAll() {
        return service.findAll();
    }

    public AuditResponse update(UUID id, AuditRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
