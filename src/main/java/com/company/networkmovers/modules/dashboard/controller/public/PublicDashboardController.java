package com.company.networkmovers.modules.dashboard.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.dashboard.dto.response.DashboardResponse;
import com.company.networkmovers.modules.dashboard.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/dashboard")
public class PublicDashboardController {

    private final DashboardService service;

    public PublicDashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DashboardResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DashboardResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
