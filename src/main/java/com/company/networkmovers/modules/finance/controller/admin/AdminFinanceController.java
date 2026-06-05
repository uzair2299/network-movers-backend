package com.company.networkmovers.modules.finance.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.finance.dto.request.FinanceRequest;
import com.company.networkmovers.modules.finance.dto.response.FinanceResponse;
import com.company.networkmovers.modules.finance.service.FinanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/finance")
public class AdminFinanceController {

    private final FinanceService service;

    public AdminFinanceController(FinanceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FinanceResponse> create(@RequestBody FinanceRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinanceResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<FinanceResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinanceResponse> update(@PathVariable UUID id, @RequestBody FinanceRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
