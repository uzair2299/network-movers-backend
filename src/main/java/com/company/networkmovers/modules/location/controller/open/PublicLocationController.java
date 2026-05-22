package com.company.networkmovers.modules.location.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.location.dto.response.LocationResponse;
import com.company.networkmovers.modules.location.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/location")
public class PublicLocationController {

    private final LocationService service;

    public PublicLocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LocationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

