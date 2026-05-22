package com.company.networkmovers.modules.dispatcher.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.dispatcher.dto.response.DispatcherResponse;
import com.company.networkmovers.modules.dispatcher.service.DispatcherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/dispatcher")
public class PublicDispatcherController {

    private final DispatcherService service;

    public PublicDispatcherController(DispatcherService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispatcherResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DispatcherResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

