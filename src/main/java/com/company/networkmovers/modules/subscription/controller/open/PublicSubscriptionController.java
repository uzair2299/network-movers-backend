package com.company.networkmovers.modules.subscription.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.subscription.dto.response.SubscriptionResponse;
import com.company.networkmovers.modules.subscription.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/subscription")
public class PublicSubscriptionController {

    private final SubscriptionService service;

    public PublicSubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

