package com.company.networkmovers.modules.route.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.route.dto.request.RouteRequest;
import com.company.networkmovers.modules.route.dto.response.RouteResponse;
import com.company.networkmovers.modules.route.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/route")
public class AdminRouteController {

    private final RouteService service;

    public AdminRouteController(RouteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RouteResponse> create(@RequestBody RouteRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RouteResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteResponse> update(@PathVariable UUID id, @RequestBody RouteRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
