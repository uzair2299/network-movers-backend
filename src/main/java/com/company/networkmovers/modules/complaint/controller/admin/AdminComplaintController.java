package com.company.networkmovers.modules.complaint.controller.admin;

import com.company.networkmovers.modules.complaint.dto.request.ComplaintRequest;
import com.company.networkmovers.modules.complaint.dto.response.ComplaintResponse;
import com.company.networkmovers.modules.complaint.service.ComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/complaint")
public class AdminComplaintController {

    private final ComplaintService service;

    public AdminComplaintController(ComplaintService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ComplaintResponse> create(@RequestBody ComplaintRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplaintResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ComplaintResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComplaintResponse> update(@PathVariable Long id, @RequestBody ComplaintRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
