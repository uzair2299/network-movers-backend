package com.company.networkmovers.modules.geofence.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.geofence.dto.response.GeofenceResponse;
import com.company.networkmovers.modules.geofence.service.GeofenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/geofence")
public class PublicGeofenceController {

    private final GeofenceService service;

    public PublicGeofenceController(GeofenceService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeofenceResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<GeofenceResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

