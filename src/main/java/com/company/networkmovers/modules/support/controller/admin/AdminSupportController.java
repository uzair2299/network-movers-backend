package com.company.networkmovers.modules.support.controller.admin;

import com.company.networkmovers.modules.support.dto.request.SupportRequest;
import com.company.networkmovers.modules.support.dto.response.SupportResponse;
import com.company.networkmovers.modules.support.service.SupportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/support")
public class AdminSupportController {

    private final SupportService service;

    public AdminSupportController(SupportService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SupportResponse> create(@RequestBody SupportRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SupportResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportResponse> update(@PathVariable Long id, @RequestBody SupportRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
