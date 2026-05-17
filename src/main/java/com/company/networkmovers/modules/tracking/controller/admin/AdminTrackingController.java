package com.company.networkmovers.modules.tracking.controller.admin;

import com.company.networkmovers.modules.tracking.dto.request.TrackingRequest;
import com.company.networkmovers.modules.tracking.dto.response.TrackingResponse;
import com.company.networkmovers.modules.tracking.service.TrackingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/tracking")
public class AdminTrackingController {

    private final TrackingService service;

    public AdminTrackingController(TrackingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TrackingResponse> create(@RequestBody TrackingRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackingResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TrackingResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackingResponse> update(@PathVariable Long id, @RequestBody TrackingRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
