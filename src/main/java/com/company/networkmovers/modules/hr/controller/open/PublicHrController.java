package com.company.networkmovers.modules.hr.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.hr.dto.response.HrResponse;
import com.company.networkmovers.modules.hr.service.HrService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/hr")
public class PublicHrController {

    private final HrService service;

    public PublicHrController(HrService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HrResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<HrResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

