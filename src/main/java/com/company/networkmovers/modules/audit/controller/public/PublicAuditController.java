package com.company.networkmovers.modules.audit.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.audit.dto.response.AuditResponse;
import com.company.networkmovers.modules.audit.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/audit")
public class PublicAuditController {

    private final AuditService service;

    public PublicAuditController(AuditService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AuditResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
