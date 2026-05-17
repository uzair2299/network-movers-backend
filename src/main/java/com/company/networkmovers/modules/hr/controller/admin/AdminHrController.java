package com.company.networkmovers.modules.hr.controller.admin;

import com.company.networkmovers.modules.hr.dto.request.HrRequest;
import com.company.networkmovers.modules.hr.dto.response.HrResponse;
import com.company.networkmovers.modules.hr.service.HrService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/hr")
public class AdminHrController {

    private final HrService service;

    public AdminHrController(HrService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HrResponse> create(@RequestBody HrRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HrResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<HrResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HrResponse> update(@PathVariable Long id, @RequestBody HrRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
