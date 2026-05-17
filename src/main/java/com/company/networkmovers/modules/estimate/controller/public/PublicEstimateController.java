package com.company.networkmovers.modules.estimate.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.estimate.dto.response.EstimateResponse;
import com.company.networkmovers.modules.estimate.service.EstimateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/estimate")
public class PublicEstimateController {

    private final EstimateService service;

    public PublicEstimateController(EstimateService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstimateResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EstimateResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
