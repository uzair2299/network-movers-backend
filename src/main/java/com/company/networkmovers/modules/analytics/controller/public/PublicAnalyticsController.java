package com.company.networkmovers.modules.analytics.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.analytics.dto.response.AnalyticsResponse;
import com.company.networkmovers.modules.analytics.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/analytics")
public class PublicAnalyticsController {

    private final AnalyticsService service;

    public PublicAnalyticsController(AnalyticsService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalyticsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AnalyticsResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
