package com.company.networkmovers.modules.ai.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.ai.dto.request.AiRequest;
import com.company.networkmovers.modules.ai.dto.response.AiResponse;
import com.company.networkmovers.modules.ai.service.AiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/ai")
public class AdminAiController {

    private final AiService service;

    public AdminAiController(AiService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AiResponse> create(@RequestBody AiRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AiResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AiResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AiResponse> update(@PathVariable UUID id, @RequestBody AiRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
