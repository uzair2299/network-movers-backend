package com.company.networkmovers.modules.trip.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.trip.dto.response.TripResponse;
import com.company.networkmovers.modules.trip.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/trip")
public class PublicTripController {

    private final TripService service;

    public PublicTripController(TripService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TripResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
