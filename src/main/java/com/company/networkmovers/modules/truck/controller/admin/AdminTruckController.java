package com.company.networkmovers.modules.truck.controller.admin;

import com.company.networkmovers.modules.truck.dto.request.TruckRequest;
import com.company.networkmovers.modules.truck.dto.response.TruckResponse;
import com.company.networkmovers.modules.truck.service.TruckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/truck")
public class AdminTruckController {

    private final TruckService service;

    public AdminTruckController(TruckService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TruckResponse> create(@RequestBody TruckRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TruckResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TruckResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TruckResponse> update(@PathVariable Long id, @RequestBody TruckRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
