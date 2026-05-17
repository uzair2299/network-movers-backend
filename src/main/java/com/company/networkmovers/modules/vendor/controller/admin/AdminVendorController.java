package com.company.networkmovers.modules.vendor.controller.admin;

import com.company.networkmovers.modules.vendor.dto.request.VendorRequest;
import com.company.networkmovers.modules.vendor.dto.response.VendorResponse;
import com.company.networkmovers.modules.vendor.service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/vendor")
public class AdminVendorController {

    private final VendorService service;

    public AdminVendorController(VendorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VendorResponse> create(@RequestBody VendorRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<VendorResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorResponse> update(@PathVariable Long id, @RequestBody VendorRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
