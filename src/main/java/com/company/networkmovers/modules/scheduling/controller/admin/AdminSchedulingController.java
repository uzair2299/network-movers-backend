package com.company.networkmovers.modules.scheduling.controller.admin;

import com.company.networkmovers.modules.scheduling.dto.request.SchedulingRequest;
import com.company.networkmovers.modules.scheduling.dto.response.SchedulingResponse;
import com.company.networkmovers.modules.scheduling.service.SchedulingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/scheduling")
public class AdminSchedulingController {

    private final SchedulingService service;

    public AdminSchedulingController(SchedulingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SchedulingResponse> create(@RequestBody SchedulingRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchedulingResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SchedulingResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchedulingResponse> update(@PathVariable Long id, @RequestBody SchedulingRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
