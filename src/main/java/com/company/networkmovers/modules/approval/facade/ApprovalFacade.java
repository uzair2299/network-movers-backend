package com.company.networkmovers.modules.approval.facade;

import com.company.networkmovers.modules.approval.dto.request.ApprovalRequest;
import com.company.networkmovers.modules.approval.dto.response.ApprovalResponse;
import com.company.networkmovers.modules.approval.service.ApprovalService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ApprovalFacade {

    private final ApprovalService service;

    public ApprovalFacade(ApprovalService service) {
        this.service = service;
    }

    public ApprovalResponse create(ApprovalRequest request) {
        return service.create(request);
    }

    public ApprovalResponse findById(Long id) {
        return service.findById(id);
    }

    public List<ApprovalResponse> findAll() {
        return service.findAll();
    }

    public ApprovalResponse update(Long id, ApprovalRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
