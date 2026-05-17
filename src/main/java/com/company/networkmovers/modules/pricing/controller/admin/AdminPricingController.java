package com.company.networkmovers.modules.pricing.controller.admin;

import com.company.networkmovers.modules.pricing.dto.request.PricingRequest;
import com.company.networkmovers.modules.pricing.dto.response.PricingResponse;
import com.company.networkmovers.modules.pricing.service.PricingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/pricing")
public class AdminPricingController {

    private final PricingService service;

    public AdminPricingController(PricingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PricingResponse> create(@RequestBody PricingRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PricingResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PricingResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PricingResponse> update(@PathVariable Long id, @RequestBody PricingRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
