package com.company.networkmovers.modules.insurance.controller.admin;

import com.company.networkmovers.modules.insurance.dto.request.InsuranceRequest;
import com.company.networkmovers.modules.insurance.dto.response.InsuranceResponse;
import com.company.networkmovers.modules.insurance.service.InsuranceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/insurance")
public class AdminInsuranceController {

    private final InsuranceService service;

    public AdminInsuranceController(InsuranceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InsuranceResponse> create(@RequestBody InsuranceRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<InsuranceResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsuranceResponse> update(@PathVariable Long id, @RequestBody InsuranceRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
