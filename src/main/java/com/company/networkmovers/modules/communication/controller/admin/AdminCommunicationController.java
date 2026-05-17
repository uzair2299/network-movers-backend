package com.company.networkmovers.modules.communication.controller.admin;

import com.company.networkmovers.modules.communication.dto.request.CommunicationRequest;
import com.company.networkmovers.modules.communication.dto.response.CommunicationResponse;
import com.company.networkmovers.modules.communication.service.CommunicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/communication")
public class AdminCommunicationController {

    private final CommunicationService service;

    public AdminCommunicationController(CommunicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CommunicationResponse> create(@RequestBody CommunicationRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunicationResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CommunicationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommunicationResponse> update(@PathVariable Long id, @RequestBody CommunicationRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
