package com.company.networkmovers.modules.maps.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.maps.dto.response.MapsResponse;
import com.company.networkmovers.modules.maps.service.MapsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/maps")
public class PublicMapsController {

    private final MapsService service;

    public PublicMapsController(MapsService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MapsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MapsResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

