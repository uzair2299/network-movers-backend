package com.company.networkmovers.modules.support.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.support.dto.response.SupportResponse;
import com.company.networkmovers.modules.support.service.SupportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/support")
public class PublicSupportController {

    private final SupportService service;

    public PublicSupportController(SupportService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SupportResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
