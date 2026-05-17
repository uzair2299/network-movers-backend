package com.company.networkmovers.modules.contract.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.contract.dto.response.ContractResponse;
import com.company.networkmovers.modules.contract.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/contract")
public class PublicContractController {

    private final ContractService service;

    public PublicContractController(ContractService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ContractResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
