package com.company.networkmovers.modules.automation.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.automation.dto.response.AutomationResponse;
import com.company.networkmovers.modules.automation.service.AutomationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/automation")
public class PublicAutomationController {

    private final AutomationService service;

    public PublicAutomationController(AutomationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutomationResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AutomationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

