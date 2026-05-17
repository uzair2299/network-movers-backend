package com.company.networkmovers.modules.mover.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.mover.dto.response.MoverResponse;
import com.company.networkmovers.modules.mover.service.MoverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/mover")
public class PublicMoverController {

    private final MoverService service;

    public PublicMoverController(MoverService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoverResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MoverResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
