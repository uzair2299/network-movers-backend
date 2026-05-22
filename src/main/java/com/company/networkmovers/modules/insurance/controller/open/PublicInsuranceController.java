package com.company.networkmovers.modules.insurance.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.insurance.dto.response.InsuranceResponse;
import com.company.networkmovers.modules.insurance.service.InsuranceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/insurance")
public class PublicInsuranceController {

    private final InsuranceService service;

    public PublicInsuranceController(InsuranceService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<InsuranceResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

