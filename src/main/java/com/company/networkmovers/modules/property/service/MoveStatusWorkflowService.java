package com.company.networkmovers.modules.property.service;

import com.company.networkmovers.modules.property.dto.request.MoveStatusWorkflowRequest;
import com.company.networkmovers.modules.property.dto.response.MoveStatusWorkflowResponse;

public interface MoveStatusWorkflowService {
    MoveStatusWorkflowResponse saveWorkflow(MoveStatusWorkflowRequest request);
    MoveStatusWorkflowResponse getWorkflow();
}
