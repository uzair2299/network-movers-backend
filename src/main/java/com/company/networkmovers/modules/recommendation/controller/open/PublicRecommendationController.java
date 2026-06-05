package com.company.networkmovers.modules.recommendation.controller.open;

import java.util.UUID; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.recommendation.dto.response.RecommendationResponse;
import com.company.networkmovers.modules.recommendation.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/recommendation")
public class PublicRecommendationController {

    private final RecommendationService service;

    public PublicRecommendationController(RecommendationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecommendationResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RecommendationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

