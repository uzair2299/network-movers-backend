package com.company.networkmovers.modules.partner.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.partner.dto.response.PartnerResponse;
import com.company.networkmovers.modules.partner.service.PartnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/partner")
public class PublicPartnerController {

    private final PartnerService service;

    public PublicPartnerController(PartnerService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartnerResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PartnerResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

