package com.company.networkmovers.modules.movingitem.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.movingitem.dto.response.MovingitemResponse;
import com.company.networkmovers.modules.movingitem.service.MovingitemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/movingitem")
public class PublicMovingitemController {

    private final MovingitemService service;

    public PublicMovingitemController(MovingitemService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovingitemResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MovingitemResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

