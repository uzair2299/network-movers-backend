package com.company.networkmovers.modules.asset.controller.admin;

import com.company.networkmovers.modules.asset.dto.request.AssetMaintenanceRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetMaintenanceResponse;
import com.company.networkmovers.modules.asset.service.AssetMaintenanceService;
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
@RequestMapping("/api/v1/admin/assets/maintenances")
@Tag(name = "Admin Asset Maintenances", description = "Admin API for managing Asset maintenances")
@RequiredArgsConstructor
public class AdminAssetMaintenanceController {

    private final AssetMaintenanceService service;

    @PostMapping
    @Operation(summary = "Schedule or log maintenance for an asset")
    public ResponseEntity<AssetMaintenanceResponse> create(
            @RequestBody AssetMaintenanceRequest request,
            @RequestParam(required = false) UUID locationId) {
        return new ResponseEntity<>(service.create(request, locationId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update maintenance log")
    public ResponseEntity<AssetMaintenanceResponse> update(@PathVariable UUID id, @RequestBody AssetMaintenanceRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get maintenance log by ID")
    public ResponseEntity<AssetMaintenanceResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Search maintenance logs with pagination")
    public ResponseEntity<Page<AssetMaintenanceResponse>> getAll(RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update maintenance status (completion updates stock)")
    public ResponseEntity<AssetMaintenanceResponse> updateStatus(
            @PathVariable UUID id,
            @RequestParam String status,
            @RequestParam UUID locationId) {
        return ResponseEntity.ok(service.updateStatus(id, status, locationId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete maintenance log")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
