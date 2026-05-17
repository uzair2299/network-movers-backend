package com.company.networkmovers.modules.approval.service;

import com.company.networkmovers.modules.approval.dto.request.ApprovalRequest;
import com.company.networkmovers.modules.approval.dto.response.ApprovalResponse;
import java.util.List;

public interface ApprovalService {
    ApprovalResponse create(ApprovalRequest request);
    ApprovalResponse findById(Long id);
    List<ApprovalResponse> findAll();
    ApprovalResponse update(Long id, ApprovalRequest request);
    void delete(Long id);
}
