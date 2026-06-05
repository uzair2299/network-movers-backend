package com.company.networkmovers.modules.configuration.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.configuration.dto.request.ConfigurationRequest;
import com.company.networkmovers.modules.configuration.dto.response.ConfigurationResponse;
import com.company.networkmovers.modules.configuration.service.ConfigurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/configuration")
public class AdminConfigurationController {

    private final ConfigurationService service;

    public AdminConfigurationController(ConfigurationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ConfigurationResponse> create(@RequestBody ConfigurationRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfigurationResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ConfigurationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfigurationResponse> update(@PathVariable UUID id, @RequestBody ConfigurationRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
