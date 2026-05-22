package com.company.networkmovers.modules.settings.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.settings.dto.response.SettingsResponse;
import com.company.networkmovers.modules.settings.service.SettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/settings")
public class PublicSettingsController {

    private final SettingsService service;

    public PublicSettingsController(SettingsService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SettingsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SettingsResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

