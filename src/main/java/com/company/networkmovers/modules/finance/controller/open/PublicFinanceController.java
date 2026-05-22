package com.company.networkmovers.modules.finance.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.finance.dto.response.FinanceResponse;
import com.company.networkmovers.modules.finance.service.FinanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/finance")
public class PublicFinanceController {

    private final FinanceService service;

    public PublicFinanceController(FinanceService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinanceResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<FinanceResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

