package com.company.networkmovers.modules.document.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.document.dto.response.DocumentResponse;
import com.company.networkmovers.modules.document.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/document")
public class PublicDocumentController {

    private final DocumentService service;

    public PublicDocumentController(DocumentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DocumentResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
