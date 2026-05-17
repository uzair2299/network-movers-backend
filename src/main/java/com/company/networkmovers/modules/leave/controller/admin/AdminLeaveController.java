package com.company.networkmovers.modules.leave.controller.admin;

import com.company.networkmovers.modules.leave.dto.request.LeaveRequest;
import com.company.networkmovers.modules.leave.dto.response.LeaveResponse;
import com.company.networkmovers.modules.leave.service.LeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/leave")
public class AdminLeaveController {

    private final LeaveService service;

    public AdminLeaveController(LeaveService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LeaveResponse> create(@RequestBody LeaveRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LeaveResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveResponse> update(@PathVariable Long id, @RequestBody LeaveRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
