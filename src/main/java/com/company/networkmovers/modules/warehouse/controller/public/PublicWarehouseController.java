package com.company.networkmovers.modules.warehouse.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.warehouse.dto.response.WarehouseResponse;
import com.company.networkmovers.modules.warehouse.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/warehouse")
public class PublicWarehouseController {

    private final WarehouseService service;

    public PublicWarehouseController(WarehouseService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<WarehouseResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
