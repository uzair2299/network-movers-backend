package com.company.networkmovers.modules.trip.controller.admin;

import com.company.networkmovers.modules.trip.dto.request.TripRequest;
import com.company.networkmovers.modules.trip.dto.response.TripResponse;
import com.company.networkmovers.modules.trip.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/trip")
public class AdminTripController {

    private final TripService service;

    public AdminTripController(TripService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TripResponse> create(@RequestBody TripRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TripResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripResponse> update(@PathVariable Long id, @RequestBody TripRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
