package com.company.networkmovers.modules.filemanagement.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.filemanagement.dto.response.FilemanagementResponse;
import com.company.networkmovers.modules.filemanagement.service.FilemanagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/filemanagement")
public class PublicFilemanagementController {

    private final FilemanagementService service;

    public PublicFilemanagementController(FilemanagementService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilemanagementResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<FilemanagementResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

