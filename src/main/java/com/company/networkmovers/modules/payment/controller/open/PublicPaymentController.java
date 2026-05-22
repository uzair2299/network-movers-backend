package com.company.networkmovers.modules.payment.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.payment.dto.response.PaymentResponse;
import com.company.networkmovers.modules.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/payment")
public class PublicPaymentController {

    private final PaymentService service;

    public PublicPaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

