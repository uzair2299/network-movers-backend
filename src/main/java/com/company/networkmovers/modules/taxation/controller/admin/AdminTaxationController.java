package com.company.networkmovers.modules.taxation.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.taxation.dto.request.TaxationRequest;
import com.company.networkmovers.modules.taxation.dto.response.TaxationResponse;
import com.company.networkmovers.modules.taxation.service.TaxationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/taxation")
public class AdminTaxationController {

    private final TaxationService service;

    public AdminTaxationController(TaxationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaxationResponse> create(@RequestBody TaxationRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaxationResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TaxationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaxationResponse> update(@PathVariable UUID id, @RequestBody TaxationRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
