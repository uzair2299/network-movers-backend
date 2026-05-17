package com.company.networkmovers.modules.identity.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.identity.dto.response.IdentityResponse;
import com.company.networkmovers.modules.identity.service.IdentityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/identity")
public class PublicIdentityController {

    private final IdentityService service;

    public PublicIdentityController(IdentityService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdentityResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<IdentityResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
