package com.company.networkmovers.modules.accounting.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.accounting.dto.response.AccountingResponse;
import com.company.networkmovers.modules.accounting.service.AccountingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/accounting")
public class PublicAccountingController {

    private final AccountingService service;

    public PublicAccountingController(AccountingService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountingResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AccountingResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

