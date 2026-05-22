package com.company.networkmovers.modules.report.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.report.dto.response.ReportResponse;
import com.company.networkmovers.modules.report.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/report")
public class PublicReportController {

    private final ReportService service;

    public PublicReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReportResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

