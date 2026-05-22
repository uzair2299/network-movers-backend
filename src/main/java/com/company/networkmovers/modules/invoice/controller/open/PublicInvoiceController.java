package com.company.networkmovers.modules.invoice.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.invoice.dto.response.InvoiceResponse;
import com.company.networkmovers.modules.invoice.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/invoice")
public class PublicInvoiceController {

    private final InvoiceService service;

    public PublicInvoiceController(InvoiceService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

