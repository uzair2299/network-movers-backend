package com.company.networkmovers.modules.pricing.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.pricing.dto.response.PricingResponse;
import com.company.networkmovers.modules.pricing.service.PricingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/pricing")
public class PublicPricingController {

    private final PricingService service;

    public PublicPricingController(PricingService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PricingResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PricingResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

