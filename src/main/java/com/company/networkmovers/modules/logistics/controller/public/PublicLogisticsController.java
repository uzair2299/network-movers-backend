package com.company.networkmovers.modules.logistics.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.logistics.dto.response.LogisticsResponse;
import com.company.networkmovers.modules.logistics.service.LogisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/logistics")
public class PublicLogisticsController {

    private final LogisticsService service;

    public PublicLogisticsController(LogisticsService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogisticsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LogisticsResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
