package com.company.networkmovers.modules.ai.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.ai.dto.response.AiResponse;
import com.company.networkmovers.modules.ai.service.AiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/ai")
public class PublicAiController {

    private final AiService service;

    public PublicAiController(AiService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AiResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AiResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

