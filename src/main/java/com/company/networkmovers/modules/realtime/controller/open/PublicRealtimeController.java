package com.company.networkmovers.modules.realtime.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.realtime.dto.response.RealtimeResponse;
import com.company.networkmovers.modules.realtime.service.RealtimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/realtime")
public class PublicRealtimeController {

    private final RealtimeService service;

    public PublicRealtimeController(RealtimeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RealtimeResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RealtimeResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

