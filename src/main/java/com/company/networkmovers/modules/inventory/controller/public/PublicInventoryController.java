package com.company.networkmovers.modules.inventory.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.inventory.dto.response.InventoryResponse;
import com.company.networkmovers.modules.inventory.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/inventory")
public class PublicInventoryController {

    private final InventoryService service;

    public PublicInventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
