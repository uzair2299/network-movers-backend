package com.company.networkmovers.modules.workflow.service;

import java.util.UUID;

import com.company.networkmovers.modules.workflow.dto.request.WorkflowRequest;
import com.company.networkmovers.modules.workflow.dto.response.WorkflowResponse;
import java.util.List;

public interface WorkflowService {
    WorkflowResponse create(WorkflowRequest request);
    WorkflowResponse findById(UUID id);
    List<WorkflowResponse> findAll();
    WorkflowResponse update(UUID id, WorkflowRequest request);
    void delete(UUID id);
}
