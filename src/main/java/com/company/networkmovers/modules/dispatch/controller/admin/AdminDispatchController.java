package com.company.networkmovers.modules.dispatch.controller.admin;

import com.company.networkmovers.modules.dispatch.dto.request.DispatchRequest;
import com.company.networkmovers.modules.dispatch.dto.response.DispatchResponse;
import com.company.networkmovers.modules.dispatch.service.DispatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/dispatch")
public class AdminDispatchController {

    private final DispatchService service;

    public AdminDispatchController(DispatchService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DispatchResponse> create(@RequestBody DispatchRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispatchResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DispatchResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispatchResponse> update(@PathVariable Long id, @RequestBody DispatchRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
