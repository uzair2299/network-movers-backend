package com.company.networkmovers.modules.scheduling.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.scheduling.dto.response.SchedulingResponse;
import com.company.networkmovers.modules.scheduling.service.SchedulingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/scheduling")
public class PublicSchedulingController {

    private final SchedulingService service;

    public PublicSchedulingController(SchedulingService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchedulingResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SchedulingResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

