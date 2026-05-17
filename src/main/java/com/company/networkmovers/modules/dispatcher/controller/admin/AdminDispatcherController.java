package com.company.networkmovers.modules.dispatcher.controller.admin;

import com.company.networkmovers.modules.dispatcher.dto.request.DispatcherRequest;
import com.company.networkmovers.modules.dispatcher.dto.response.DispatcherResponse;
import com.company.networkmovers.modules.dispatcher.service.DispatcherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/dispatcher")
public class AdminDispatcherController {

    private final DispatcherService service;

    public AdminDispatcherController(DispatcherService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DispatcherResponse> create(@RequestBody DispatcherRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispatcherResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DispatcherResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispatcherResponse> update(@PathVariable Long id, @RequestBody DispatcherRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
