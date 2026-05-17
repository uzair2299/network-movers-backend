package com.company.networkmovers.modules.workflow.service;

import com.company.networkmovers.modules.workflow.dto.request.WorkflowRequest;
import com.company.networkmovers.modules.workflow.dto.response.WorkflowResponse;
import java.util.List;

public interface WorkflowService {
    WorkflowResponse create(WorkflowRequest request);
    WorkflowResponse findById(Long id);
    List<WorkflowResponse> findAll();
    WorkflowResponse update(Long id, WorkflowRequest request);
    void delete(Long id);
}
