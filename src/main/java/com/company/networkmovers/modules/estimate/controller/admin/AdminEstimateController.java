package com.company.networkmovers.modules.estimate.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.estimate.dto.request.EstimateRequest;
import com.company.networkmovers.modules.estimate.dto.response.EstimateResponse;
import com.company.networkmovers.modules.estimate.service.EstimateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/estimate")
public class AdminEstimateController {

    private final EstimateService service;

    public AdminEstimateController(EstimateService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EstimateResponse> create(@RequestBody EstimateRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstimateResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EstimateResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstimateResponse> update(@PathVariable UUID id, @RequestBody EstimateRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
