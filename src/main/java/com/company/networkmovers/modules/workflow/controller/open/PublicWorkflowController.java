package com.company.networkmovers.modules.workflow.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.workflow.dto.response.WorkflowResponse;
import com.company.networkmovers.modules.workflow.service.WorkflowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/workflow")
public class PublicWorkflowController {

    private final WorkflowService service;

    public PublicWorkflowController(WorkflowService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkflowResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<WorkflowResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

