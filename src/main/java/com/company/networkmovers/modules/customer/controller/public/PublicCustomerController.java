package com.company.networkmovers.modules.customer.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.customer.dto.response.CustomerResponse;
import com.company.networkmovers.modules.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/customer")
public class PublicCustomerController {

    private final CustomerService service;

    public PublicCustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
