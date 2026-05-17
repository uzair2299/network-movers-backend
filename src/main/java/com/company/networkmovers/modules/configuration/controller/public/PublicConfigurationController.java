package com.company.networkmovers.modules.configuration.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.configuration.dto.response.ConfigurationResponse;
import com.company.networkmovers.modules.configuration.service.ConfigurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/configuration")
public class PublicConfigurationController {

    private final ConfigurationService service;

    public PublicConfigurationController(ConfigurationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfigurationResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ConfigurationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
