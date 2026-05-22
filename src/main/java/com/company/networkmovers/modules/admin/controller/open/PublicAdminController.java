package com.company.networkmovers.modules.admin.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.admin.dto.response.AdminResponse;
import com.company.networkmovers.modules.admin.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/admin")
public class PublicAdminController {

    private final AdminService service;

    public PublicAdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AdminResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

