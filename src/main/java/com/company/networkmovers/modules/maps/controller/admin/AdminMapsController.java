package com.company.networkmovers.modules.maps.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.maps.dto.request.MapsRequest;
import com.company.networkmovers.modules.maps.dto.response.MapsResponse;
import com.company.networkmovers.modules.maps.service.MapsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/maps")
public class AdminMapsController {

    private final MapsService service;

    public AdminMapsController(MapsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MapsResponse> create(@RequestBody MapsRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MapsResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MapsResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MapsResponse> update(@PathVariable UUID id, @RequestBody MapsRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
