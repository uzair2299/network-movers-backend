package com.company.networkmovers.modules.payroll.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.payroll.dto.response.PayrollResponse;
import com.company.networkmovers.modules.payroll.service.PayrollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/payroll")
public class PublicPayrollController {

    private final PayrollService service;

    public PublicPayrollController(PayrollService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PayrollResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PayrollResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
