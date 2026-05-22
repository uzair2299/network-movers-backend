package com.company.networkmovers.modules.communication.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.communication.dto.response.CommunicationResponse;
import com.company.networkmovers.modules.communication.service.CommunicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/communication")
public class PublicCommunicationController {

    private final CommunicationService service;

    public PublicCommunicationController(CommunicationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunicationResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CommunicationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

