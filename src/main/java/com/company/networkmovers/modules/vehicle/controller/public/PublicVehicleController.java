package com.company.networkmovers.modules.vehicle.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.vehicle.dto.response.VehicleResponse;
import com.company.networkmovers.modules.vehicle.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/vehicle")
public class PublicVehicleController {

    private final VehicleService service;

    public PublicVehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
