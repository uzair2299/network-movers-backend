package com.company.networkmovers.modules.asset.controller.admin;

import com.company.networkmovers.modules.asset.dto.request.EmployeeAssetAssignmentRequest;
import com.company.networkmovers.modules.asset.dto.response.EmployeeAssetAssignmentResponse;
import com.company.networkmovers.modules.asset.service.EmployeeAssetAssignmentService;
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
@RequestMapping("/api/v1/admin/assets/assignments")
@Tag(name = "Admin Employee Asset Assignments", description = "Admin API for managing Asset assignments to employees")
@RequiredArgsConstructor
public class AdminEmployeeAssetAssignmentController {

    private final EmployeeAssetAssignmentService service;

    @PostMapping
    @Operation(summary = "Assign an asset to an employee")
    public ResponseEntity<EmployeeAssetAssignmentResponse> create(
            @RequestBody EmployeeAssetAssignmentRequest request,
            @RequestParam(required = false) UUID locationId) {
        return new ResponseEntity<>(service.create(request, locationId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update asset assignment log")
    public ResponseEntity<EmployeeAssetAssignmentResponse> update(@PathVariable UUID id, @RequestBody EmployeeAssetAssignmentRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get assignment log by ID")
    public ResponseEntity<EmployeeAssetAssignmentResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Search employee asset assignments with pagination")
    public ResponseEntity<Page<EmployeeAssetAssignmentResponse>> getAll(RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update assignment status (returning updates stock)")
    public ResponseEntity<EmployeeAssetAssignmentResponse> updateStatus(
            @PathVariable UUID id,
            @RequestParam String status,
            @RequestParam UUID locationId,
            @RequestParam(required = false) String conditionOnReturn) {
        return ResponseEntity.ok(service.updateStatus(id, status, locationId, conditionOnReturn));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete assignment log")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
