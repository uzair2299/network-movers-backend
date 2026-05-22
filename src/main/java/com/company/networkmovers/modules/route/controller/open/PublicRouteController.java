package com.company.networkmovers.modules.route.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.route.dto.response.RouteResponse;
import com.company.networkmovers.modules.route.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/route")
public class PublicRouteController {

    private final RouteService service;

    public PublicRouteController(RouteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RouteResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

