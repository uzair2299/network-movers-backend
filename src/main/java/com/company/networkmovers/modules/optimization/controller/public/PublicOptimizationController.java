package com.company.networkmovers.modules.optimization.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.optimization.dto.response.OptimizationResponse;
import com.company.networkmovers.modules.optimization.service.OptimizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/optimization")
public class PublicOptimizationController {

    private final OptimizationService service;

    public PublicOptimizationController(OptimizationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OptimizationResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<OptimizationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
