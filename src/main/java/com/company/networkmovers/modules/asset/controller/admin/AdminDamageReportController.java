package com.company.networkmovers.modules.asset.controller.admin;

import com.company.networkmovers.modules.asset.dto.request.DamageReportRequest;
import com.company.networkmovers.modules.asset.dto.response.DamageReportResponse;
import com.company.networkmovers.modules.asset.service.DamageReportService;
import com.company.networkmovers.shared.dto.RequestParamDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/assets/damage-reports")
@Tag(name = "Admin Damage Reports", description = "Admin API for managing Asset Damage Reports")
@RequiredArgsConstructor
public class AdminDamageReportController {

    private final DamageReportService service;

    @PostMapping
    @Operation(summary = "Report damaged asset")
    public ResponseEntity<DamageReportResponse> create(@RequestBody DamageReportRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update damage report log")
    public ResponseEntity<DamageReportResponse> update(@PathVariable UUID id, @RequestBody DamageReportRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get damage report by ID")
    public ResponseEntity<DamageReportResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Search damage reports with pagination")
    public ResponseEntity<Page<DamageReportResponse>> getAll(RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete damage report")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
