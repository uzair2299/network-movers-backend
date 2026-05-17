package com.company.networkmovers.modules.geofence.controller.admin;

import com.company.networkmovers.modules.geofence.dto.request.GeofenceRequest;
import com.company.networkmovers.modules.geofence.dto.response.GeofenceResponse;
import com.company.networkmovers.modules.geofence.service.GeofenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/geofence")
public class AdminGeofenceController {

    private final GeofenceService service;

    public AdminGeofenceController(GeofenceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<GeofenceResponse> create(@RequestBody GeofenceRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeofenceResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<GeofenceResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeofenceResponse> update(@PathVariable Long id, @RequestBody GeofenceRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
