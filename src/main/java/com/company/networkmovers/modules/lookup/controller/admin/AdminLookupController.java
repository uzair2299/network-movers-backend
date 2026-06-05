package com.company.networkmovers.modules.lookup.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.lookup.dto.request.LookupRequest;
import com.company.networkmovers.modules.lookup.dto.response.LookupResponse;
import com.company.networkmovers.modules.lookup.service.LookupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/lookup")
public class AdminLookupController {

    private final LookupService service;

    public AdminLookupController(LookupService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LookupResponse> create(@RequestBody LookupRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LookupResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LookupResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LookupResponse> update(@PathVariable UUID id, @RequestBody LookupRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
