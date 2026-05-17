package com.company.networkmovers.modules.fleet.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.fleet.dto.response.FleetResponse;
import com.company.networkmovers.modules.fleet.service.FleetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/fleet")
public class PublicFleetController {

    private final FleetService service;

    public PublicFleetController(FleetService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FleetResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<FleetResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
