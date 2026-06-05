package com.company.networkmovers.modules.audit.service;

import java.util.UUID;

import com.company.networkmovers.modules.audit.dto.request.AuditRequest;
import com.company.networkmovers.modules.audit.dto.response.AuditResponse;
import java.util.List;

public interface AuditService {
    AuditResponse create(AuditRequest request);
    AuditResponse findById(UUID id);
    List<AuditResponse> findAll();
    AuditResponse update(UUID id, AuditRequest request);
    void delete(UUID id);
}
