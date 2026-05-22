package com.company.networkmovers.modules.vendor.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.vendor.dto.response.VendorResponse;
import com.company.networkmovers.modules.vendor.service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/vendor")
public class PublicVendorController {

    private final VendorService service;

    public PublicVendorController(VendorService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<VendorResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

