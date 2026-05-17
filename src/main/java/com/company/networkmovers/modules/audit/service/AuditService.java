package com.company.networkmovers.modules.audit.service;

import com.company.networkmovers.modules.audit.dto.request.AuditRequest;
import com.company.networkmovers.modules.audit.dto.response.AuditResponse;
import java.util.List;

public interface AuditService {
    AuditResponse create(AuditRequest request);
    AuditResponse findById(Long id);
    List<AuditResponse> findAll();
    AuditResponse update(Long id, AuditRequest request);
    void delete(Long id);
}
