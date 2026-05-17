package com.company.networkmovers.modules.filemanagement.controller.admin;

import com.company.networkmovers.modules.filemanagement.dto.request.FilemanagementRequest;
import com.company.networkmovers.modules.filemanagement.dto.response.FilemanagementResponse;
import com.company.networkmovers.modules.filemanagement.service.FilemanagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/filemanagement")
public class AdminFilemanagementController {

    private final FilemanagementService service;

    public AdminFilemanagementController(FilemanagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FilemanagementResponse> create(@RequestBody FilemanagementRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilemanagementResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<FilemanagementResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilemanagementResponse> update(@PathVariable Long id, @RequestBody FilemanagementRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
