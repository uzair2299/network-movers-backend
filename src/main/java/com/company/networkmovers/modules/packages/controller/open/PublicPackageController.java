package com.company.networkmovers.modules.packages.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.packages.dto.response.PackageResponse;
import com.company.networkmovers.modules.packages.service.PackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/package")
public class PublicPackageController {

    private final PackageService service;

    public PublicPackageController(PackageService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PackageResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

