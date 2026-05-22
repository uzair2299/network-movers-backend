package com.company.networkmovers.modules.promotion.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.promotion.dto.response.PromotionResponse;
import com.company.networkmovers.modules.promotion.service.PromotionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/promotion")
public class PublicPromotionController {

    private final PromotionService service;

    public PublicPromotionController(PromotionService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PromotionResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

