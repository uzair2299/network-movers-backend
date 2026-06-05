package com.company.networkmovers.modules.fraud.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.fraud.dto.request.FraudRequest;
import com.company.networkmovers.modules.fraud.dto.response.FraudResponse;
import com.company.networkmovers.modules.fraud.service.FraudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/fraud")
public class AdminFraudController {

    private final FraudService service;

    public AdminFraudController(FraudService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FraudResponse> create(@RequestBody FraudRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FraudResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<FraudResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FraudResponse> update(@PathVariable UUID id, @RequestBody FraudRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
