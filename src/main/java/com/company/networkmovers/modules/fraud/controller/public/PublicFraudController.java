package com.company.networkmovers.modules.fraud.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.fraud.dto.response.FraudResponse;
import com.company.networkmovers.modules.fraud.service.FraudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/fraud")
public class PublicFraudController {

    private final FraudService service;

    public PublicFraudController(FraudService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FraudResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<FraudResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
