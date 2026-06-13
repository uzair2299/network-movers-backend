package com.company.networkmovers.modules.asset.controller.admin;

import com.company.networkmovers.modules.asset.dto.request.AssetRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetResponse;
import com.company.networkmovers.modules.asset.service.AssetService;
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
@RequestMapping("/api/v1/admin/assets")
@Tag(name = "Admin Assets Registry", description = "Admin API for managing Assets Registry")
@RequiredArgsConstructor
public class AdminAssetController {

    private final AssetService service;

    @PostMapping
    @Operation(summary = "Register a new Asset")
    public ResponseEntity<AssetResponse> create(@RequestBody AssetRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Asset registration")
    public ResponseEntity<AssetResponse> update(@PathVariable UUID id, @RequestBody AssetRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Asset details by ID")
    public ResponseEntity<AssetResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Search Asset Registry with pagination")
    public ResponseEntity<Page<AssetResponse>> getAll(RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update Asset operational status")
    public ResponseEntity<AssetResponse> updateStatus(@PathVariable UUID id, @RequestParam String status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Soft delete an Asset registry record")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
