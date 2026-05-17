package com.company.networkmovers.modules.backup.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.backup.dto.response.BackupResponse;
import com.company.networkmovers.modules.backup.service.BackupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/backup")
public class PublicBackupController {

    private final BackupService service;

    public PublicBackupController(BackupService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BackupResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<BackupResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
