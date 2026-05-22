package com.company.networkmovers.modules.notification.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.notification.dto.response.NotificationResponse;
import com.company.networkmovers.modules.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/notification")
public class PublicNotificationController {

    private final NotificationService service;

    public PublicNotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

