package com.company.networkmovers.modules.quotation.controller.open;

import java.util.UUID; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.quotation.dto.response.QuotationResponse;
import com.company.networkmovers.modules.quotation.service.QuotationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/quotation")
public class PublicQuotationController {

    private final QuotationService service;

    public PublicQuotationController(QuotationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuotationResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<QuotationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

