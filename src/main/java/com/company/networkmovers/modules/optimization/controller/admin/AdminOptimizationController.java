package com.company.networkmovers.modules.optimization.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.optimization.dto.request.OptimizationRequest;
import com.company.networkmovers.modules.optimization.dto.response.OptimizationResponse;
import com.company.networkmovers.modules.optimization.service.OptimizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/optimization")
public class AdminOptimizationController {

    private final OptimizationService service;

    public AdminOptimizationController(OptimizationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OptimizationResponse> create(@RequestBody OptimizationRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OptimizationResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<OptimizationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OptimizationResponse> update(@PathVariable UUID id, @RequestBody OptimizationRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
