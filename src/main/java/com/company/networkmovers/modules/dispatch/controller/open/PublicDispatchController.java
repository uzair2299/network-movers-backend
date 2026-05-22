package com.company.networkmovers.modules.dispatch.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.dispatch.dto.response.DispatchResponse;
import com.company.networkmovers.modules.dispatch.service.DispatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/dispatch")
public class PublicDispatchController {

    private final DispatchService service;

    public PublicDispatchController(DispatchService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispatchResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DispatchResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

