package com.company.networkmovers.modules.tracking.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.tracking.dto.response.TrackingResponse;
import com.company.networkmovers.modules.tracking.service.TrackingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/tracking")
public class PublicTrackingController {

    private final TrackingService service;

    public PublicTrackingController(TrackingService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackingResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TrackingResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
