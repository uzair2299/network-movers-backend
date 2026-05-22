package com.company.networkmovers.modules.lookup.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.lookup.dto.response.LookupResponse;
import com.company.networkmovers.modules.lookup.service.LookupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/lookup")
public class PublicLookupController {

    private final LookupService service;

    public PublicLookupController(LookupService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LookupResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LookupResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

