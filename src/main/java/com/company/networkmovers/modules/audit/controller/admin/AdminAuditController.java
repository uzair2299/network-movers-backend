package com.company.networkmovers.modules.audit.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.audit.dto.request.AuditRequest;
import com.company.networkmovers.modules.audit.dto.response.AuditResponse;
import com.company.networkmovers.modules.audit.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/audit")
public class AdminAuditController {

    private final AuditService service;

    public AdminAuditController(AuditService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AuditResponse> create(@RequestBody AuditRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AuditResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditResponse> update(@PathVariable UUID id, @RequestBody AuditRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
