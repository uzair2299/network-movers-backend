package com.company.networkmovers.modules.recommendation.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.recommendation.dto.request.RecommendationRequest;
import com.company.networkmovers.modules.recommendation.dto.response.RecommendationResponse;
import com.company.networkmovers.modules.recommendation.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/recommendation")
public class AdminRecommendationController {

    private final RecommendationService service;

    public AdminRecommendationController(RecommendationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RecommendationResponse> create(@RequestBody RecommendationRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecommendationResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RecommendationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecommendationResponse> update(@PathVariable UUID id, @RequestBody RecommendationRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
