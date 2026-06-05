package com.company.networkmovers.modules.leave.controller.open;

import java.util.UUID; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.leave.dto.response.LeaveResponse;
import com.company.networkmovers.modules.leave.service.LeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/leave")
public class PublicLeaveController {

    private final LeaveService service;

    public PublicLeaveController(LeaveService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LeaveResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

