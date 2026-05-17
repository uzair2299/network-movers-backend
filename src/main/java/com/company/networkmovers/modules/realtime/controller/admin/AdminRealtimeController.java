package com.company.networkmovers.modules.realtime.controller.admin;

import com.company.networkmovers.modules.realtime.dto.request.RealtimeRequest;
import com.company.networkmovers.modules.realtime.dto.response.RealtimeResponse;
import com.company.networkmovers.modules.realtime.service.RealtimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/realtime")
public class AdminRealtimeController {

    private final RealtimeService service;

    public AdminRealtimeController(RealtimeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RealtimeResponse> create(@RequestBody RealtimeRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RealtimeResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RealtimeResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RealtimeResponse> update(@PathVariable Long id, @RequestBody RealtimeRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
