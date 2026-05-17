package com.company.networkmovers.modules.claims.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.claims.dto.response.ClaimsResponse;
import com.company.networkmovers.modules.claims.service.ClaimsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/claims")
public class PublicClaimsController {

    private final ClaimsService service;

    public PublicClaimsController(ClaimsService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaimsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClaimsResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
