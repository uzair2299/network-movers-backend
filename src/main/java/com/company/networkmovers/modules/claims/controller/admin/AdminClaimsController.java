package com.company.networkmovers.modules.claims.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.claims.dto.request.ClaimsRequest;
import com.company.networkmovers.modules.claims.dto.response.ClaimsResponse;
import com.company.networkmovers.modules.claims.service.ClaimsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/claims")
public class AdminClaimsController {

    private final ClaimsService service;

    public AdminClaimsController(ClaimsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClaimsResponse> create(@RequestBody ClaimsRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaimsResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClaimsResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClaimsResponse> update(@PathVariable UUID id, @RequestBody ClaimsRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
