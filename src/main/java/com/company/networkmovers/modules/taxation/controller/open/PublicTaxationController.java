package com.company.networkmovers.modules.taxation.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.taxation.dto.response.TaxationResponse;
import com.company.networkmovers.modules.taxation.service.TaxationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/taxation")
public class PublicTaxationController {

    private final TaxationService service;

    public PublicTaxationController(TaxationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaxationResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TaxationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

