package com.company.networkmovers.modules.admin.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.admin.dto.request.AdminRequest;
import com.company.networkmovers.modules.admin.dto.response.AdminResponse;
import com.company.networkmovers.modules.admin.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/admin")
public class AdminAdminController {

    private final AdminService service;

    public AdminAdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AdminResponse> create(@RequestBody AdminRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AdminResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminResponse> update(@PathVariable UUID id, @RequestBody AdminRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
