package com.company.networkmovers.modules.driver.controller.admin;

import com.company.networkmovers.modules.driver.dto.request.DriverRequest;
import com.company.networkmovers.modules.driver.dto.response.DriverResponse;
import com.company.networkmovers.modules.driver.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/driver")
public class AdminDriverController {

    private final DriverService service;

    public AdminDriverController(DriverService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DriverResponse> create(@RequestBody DriverRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DriverResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponse> update(@PathVariable Long id, @RequestBody DriverRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
