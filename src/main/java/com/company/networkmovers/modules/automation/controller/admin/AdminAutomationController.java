package com.company.networkmovers.modules.automation.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.automation.dto.request.AutomationRequest;
import com.company.networkmovers.modules.automation.dto.response.AutomationResponse;
import com.company.networkmovers.modules.automation.service.AutomationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/automation")
public class AdminAutomationController {

    private final AutomationService service;

    public AdminAutomationController(AutomationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AutomationResponse> create(@RequestBody AutomationRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutomationResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AutomationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutomationResponse> update(@PathVariable UUID id, @RequestBody AutomationRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
