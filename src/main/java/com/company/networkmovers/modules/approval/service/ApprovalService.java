package com.company.networkmovers.modules.approval.service;

import java.util.UUID;

import com.company.networkmovers.modules.approval.dto.request.ApprovalRequest;
import com.company.networkmovers.modules.approval.dto.response.ApprovalResponse;
import java.util.List;

public interface ApprovalService {
    ApprovalResponse create(ApprovalRequest request);
    ApprovalResponse findById(UUID id);
    List<ApprovalResponse> findAll();
    ApprovalResponse update(UUID id, ApprovalRequest request);
    void delete(UUID id);
}
