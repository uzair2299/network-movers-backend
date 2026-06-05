package com.company.networkmovers.modules.truck.controller.open;

import java.util.UUID; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.truck.dto.response.TruckResponse;
import com.company.networkmovers.modules.truck.service.TruckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/truck")
public class PublicTruckController {

    private final TruckService service;

    public PublicTruckController(TruckService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TruckResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TruckResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

