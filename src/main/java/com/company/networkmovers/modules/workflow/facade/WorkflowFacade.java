package com.company.networkmovers.modules.workflow.facade;

import com.company.networkmovers.modules.workflow.dto.request.WorkflowRequest;
import com.company.networkmovers.modules.workflow.dto.response.WorkflowResponse;
import com.company.networkmovers.modules.workflow.service.WorkflowService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class WorkflowFacade {

    private final WorkflowService service;

    public WorkflowFacade(WorkflowService service) {
        this.service = service;
    }

    public WorkflowResponse create(WorkflowRequest request) {
        return service.create(request);
    }

    public WorkflowResponse findById(Long id) {
        return service.findById(id);
    }

    public List<WorkflowResponse> findAll() {
        return service.findAll();
    }

    public WorkflowResponse update(Long id, WorkflowRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
