package com.company.networkmovers.modules.identity.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.identity.dto.request.IdentityRequest;
import com.company.networkmovers.modules.identity.dto.response.IdentityResponse;
import com.company.networkmovers.modules.identity.service.IdentityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/identity")
public class AdminIdentityController {

    private final IdentityService service;

    public AdminIdentityController(IdentityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<IdentityResponse> create(@RequestBody IdentityRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdentityResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<IdentityResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdentityResponse> update(@PathVariable UUID id, @RequestBody IdentityRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
