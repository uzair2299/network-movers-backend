package com.company.networkmovers.modules.driver.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.driver.dto.response.DriverResponse;
import com.company.networkmovers.modules.driver.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/driver")
public class PublicDriverController {

    private final DriverService service;

    public PublicDriverController(DriverService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DriverResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
