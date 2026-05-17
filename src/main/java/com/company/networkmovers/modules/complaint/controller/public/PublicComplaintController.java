package com.company.networkmovers.modules.complaint.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.complaint.dto.response.ComplaintResponse;
import com.company.networkmovers.modules.complaint.service.ComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/complaint")
public class PublicComplaintController {

    private final ComplaintService service;

    public PublicComplaintController(ComplaintService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplaintResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ComplaintResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
